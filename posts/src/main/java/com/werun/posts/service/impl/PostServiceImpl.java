package com.werun.posts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.werun.common.core.request.Result;
import com.werun.posts.DTO.PageModel;
import com.werun.posts.DTO.PostDTO;
import com.werun.posts.DTO.PostUpdateDTO;
import com.werun.posts.VO.PostVO;
import com.werun.posts.domain.Posts;
import com.werun.posts.mapper.LabelMapper;
import com.werun.posts.mapper.PostsMapper;
import com.werun.posts.service.IPostService;
import com.werun.posts.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostServiceImpl extends ServiceImpl<PostsMapper, Posts> implements IPostService {
    @Autowired
    public PostsMapper postsMapper;

    @Autowired
    public LabelMapper labelMapper;

    /**
     * 创建帖子
     *
     * @param postDTO
     * @return
     */
    @Override
    public Result createPost(PostDTO postDTO) {
        //1. 完善帖子信息
        Posts post = new Posts();
        //1.1. 获取当前用户信息
        post.setAuthorId(SecurityUtils.getUserId());

        //1.2. 获取发布时间
        if (postDTO.isScheduled()) {
            post.setCreatedAt(postDTO.getScheduledTime());
        } else {
            LocalDateTime now = LocalDateTime.now();
            post.setCreatedAt(now);

        }

        //1.3. 默认可见
        post.setVisible(true);

        //1.4. 获取前端传来的信息
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setLabelId(postDTO.getLabelId());

        //2. 放入库中
        postsMapper.insert(post);
        PostVO postVO = new PostVO();
        postVO.setPostId(String.valueOf(post.getPostId()));
        postVO.setTitle(post.getTitle());
        postVO.setAuthorId(post.getAuthorId());
        postVO.setContent(post.getContent());
        postVO.setCreatedAt(post.getCreatedAt());
        postVO.setLabelId(post.getLabelId());

        return Result.ok(postVO, "成功创建帖子！");
    }


    /**
     * 为帖子添加图片
     *
     * @param picture
     * @return
     */
    @Override
    public Result uploadPicture(Long postId, byte[] picture) {
        //1. 校验身份
        Posts post = postsMapper.selectPostByPostId(postId);
        if (!post.getAuthorId().equals(SecurityUtils.getUserId())) {
            //1.1. 无上传权限
            return Result.fail("upload failed！");
        }
        //2. 上传图像
        post.setPicture(picture);
        postsMapper.updateById(post);
        return Result.ok("upload successfully!");
    }


    /**
     * 删除帖子
     *
     * @return
     */
    @Override
    public Result deletePost(Long postId) {
        //1. 身份、可见状态鉴权
        Posts post = postsMapper.selectPostByPostId(postId);
        if (post == null || !post.isVisible()) {
            //找不到帖子
            return Result.fail("Post not Found！");
        }
        if (!post.getAuthorId().equals(SecurityUtils.getUserId())) {
            //无删除权限
            return Result.fail("Delete failed！");
        }

        //2. 更改状态
        post.setVisible(false);
        postsMapper.updateById(post);

        //3. 删除成功
        return Result.ok("The post has been successfully deleted!");
    }

    /**
     * 查询个人帖子
     *
     * @return
     */
    @Override
    public Result readPostByAuthor(PageModel pageModel) {
        //1. 获取用户id
        Long userId = SecurityUtils.getUserId();

        //2. 查询帖子
        QueryWrapper<Posts> wrapper = new QueryWrapper<>();
        wrapper.eq("visible", true);
        wrapper.eq("author_id", userId);
        wrapper.lt("created_at", LocalDateTime.now());
        Page<Posts> page = new Page<>(pageModel.getPageNo(), pageModel.getPageSize());
        IPage<Posts> postPage = this.page(page, wrapper);

        //3. 转换为 PostVO 分页对象
        List<PostVO> voList = postPage.getRecords().stream().map(post -> {
            PostVO vo = new PostVO();
            vo.setPostId(String.valueOf(post.getPostId()));
            vo.setTitle(post.getTitle());
            vo.setAuthorId(post.getAuthorId());
            vo.setContent(post.getContent());
            vo.setCreatedAt(post.getCreatedAt());
            vo.setLabelId(post.getLabelId());
            vo.setNumberOfComments(post.getNumberOfComments());
            vo.setNumberOfLikes(post.getNumberOfLikes());
            return vo;
        }).collect(Collectors.toList());

        //4. 构造新的分页结果
        Page<PostVO> voPage = new Page<>();
        voPage.setCurrent(postPage.getCurrent());
        voPage.setSize(postPage.getSize());
        voPage.setTotal(postPage.getTotal());
        voPage.setPages(postPage.getPages());
        voPage.setRecords(voList);

        return Result.ok(voPage, "query successfully!");
    }

    /**
     * 条件查询
     *
     * @param postId
     * @param LabelContent
     * @param PostContent
     * @param pageModel
     * @return
     */
    @Override
    public Result readPostByConditions(Long postId, String LabelContent, String PostContent, PageModel pageModel) {
        //1. 设置查询条件
        QueryWrapper<Posts> wrapper = new QueryWrapper<>();
            wrapper.eq("visible", true);
            wrapper.lt("created_at", LocalDateTime.now());

            //条件1：帖子id
            if (postId != null) wrapper.eq("post_id", postId);

            //条件2：标签
            if (LabelContent != null && !LabelContent.trim().isEmpty()) {
                Long labelId = labelMapper.selectLabelContentByContent(LabelContent).getLabelId();
                wrapper.eq("label_id", labelId);
            }

            //条件3：帖子内容
            if (PostContent != null && !PostContent.trim().isEmpty()) wrapper.like("content", PostContent);
        System.out.println(wrapper.getSqlSegment());


        Page<Posts> page = new Page<>(pageModel.getPageNo(), pageModel.getPageSize());
        IPage<Posts> postPage = this.page(page, wrapper);

        //2. 转换为 PostVO 分页对象
        List<PostVO> voList = postPage.getRecords().stream().map(post -> {
            PostVO vo = new PostVO();
            vo.setPostId(String.valueOf(post.getPostId()));
            vo.setTitle(post.getTitle());
            vo.setAuthorId(post.getAuthorId());
            vo.setContent(post.getContent());
            vo.setCreatedAt(post.getCreatedAt());
            vo.setLabelId(post.getLabelId());
            vo.setNumberOfComments(post.getNumberOfComments());
            vo.setNumberOfLikes(post.getNumberOfLikes());
            return vo;
        }).collect(Collectors.toList());

        //3. 找不到对应结果
        if (postPage.getTotal() == 0) {
            return Result.fail(404, "not Found");
        }

        //4. 构造新的分页结果
        Page<PostVO> voPage = new Page<>();
        voPage.setCurrent(postPage.getCurrent());
        voPage.setSize(postPage.getSize());
        voPage.setTotal(postPage.getTotal());
        voPage.setPages(postPage.getPages());
        voPage.setRecords(voList);
        return Result.ok(voPage, "query successfully!");
    }

    /**
     * 编辑帖子
     *
     * @param postUpdateDTO
     * @return
     */
    @Override
    public Result updatePost(PostUpdateDTO postUpdateDTO) {
        //1. 查询到原贴
        Posts post = postsMapper.selectPostByPostId(postUpdateDTO.getPostId());
        if (!post.isVisible()) {
            return Result.fail("not found");
        }

        //2. 编辑帖子
        post.setTitle(postUpdateDTO.getTitle());
        post.setContent(postUpdateDTO.getContent());
        post.setLabelId(postUpdateDTO.getLabelId());
        postsMapper.updateById(post);

        //3.  生成视图对象
        PostVO postVO = new PostVO();
        postVO.setPostId(String.valueOf(post.getPostId()));
        postVO.setTitle(post.getTitle());
        postVO.setAuthorId(post.getAuthorId());
        postVO.setContent(post.getContent());
        postVO.setCreatedAt(post.getCreatedAt());
        postVO.setLabelId(post.getLabelId());
        postVO.setNumberOfComments(post.getNumberOfComments());
        postVO.setNumberOfLikes(post.getNumberOfLikes());

        return Result.ok(postVO, "update successfully!");
    }

    /**
     * 展示所有帖子
     *
     * @return
     */
    @Override
    public Result showAllPosts(PageModel pageModel) {
        //1. 查询帖子
        QueryWrapper<Posts> wrapper = new QueryWrapper<>();
        wrapper.eq("visible", true);
        Page<Posts> page = new Page<>(pageModel.getPageNo(), pageModel.getPageSize());
        IPage<Posts> postPage = this.page(page, wrapper);

        //2. 转换为 PostVO 分页对象
        List<PostVO> voList = postPage.getRecords().stream().map(post -> {
            PostVO vo = new PostVO();
            vo.setPostId(String.valueOf(post.getPostId()));
            vo.setTitle(post.getTitle());
            vo.setAuthorId(post.getAuthorId());
            vo.setContent(post.getContent());
            vo.setCreatedAt(post.getCreatedAt());
            vo.setLabelId(post.getLabelId());
            vo.setNumberOfComments(post.getNumberOfComments());
            vo.setNumberOfLikes(post.getNumberOfLikes());
            return vo;
        }).collect(Collectors.toList());

        //3. 构造新的分页结果
        Page<PostVO> voPage = new Page<>();
        voPage.setCurrent(postPage.getCurrent());
        voPage.setSize(postPage.getSize());
        voPage.setTotal(postPage.getTotal());
        voPage.setPages(postPage.getPages());
        voPage.setRecords(voList);

        return Result.ok(voPage, "query successfully!");
    }

    /**
     * 推送
     *
     * @param pageModel
     * @return
     */
    @Override
    public Result push(PageModel pageModel) {
        //1. 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        //2. 分页展示发表5分钟内的所有帖子
        QueryWrapper<Posts> wrapper = new QueryWrapper<>();
        wrapper.eq("visible", true);
        wrapper.between("created_at", now.minusMinutes(5), now);
//        wrapper.orderByDesc("number_of_likes");
        Page<Posts> page = new Page<>(pageModel.getPageNo(), pageModel.getPageSize());
        IPage<Posts> postPage = this.page(page, wrapper);

        //3. 转换为 PostVO 分页对象
        List<PostVO> voList = postPage.getRecords().stream().map(post -> {
            PostVO vo = new PostVO();
            vo.setPostId(String.valueOf(post.getPostId()));
            vo.setTitle(post.getTitle());
            vo.setAuthorId(post.getAuthorId());
            vo.setContent(post.getContent());
            vo.setCreatedAt(post.getCreatedAt());
            vo.setLabelId(post.getLabelId());
            vo.setNumberOfComments(post.getNumberOfComments());
            vo.setNumberOfLikes(post.getNumberOfLikes());
//            vo.setPictureBase64("data:image/jpeg;base64,"+ Base64.getEncoder().encodeToString(post.getPicture()));
            return vo;
        }).collect(Collectors.toList());

        //4. 构造新的分页结果
        Page<PostVO> voPage = new Page<>();
        voPage.setCurrent(postPage.getCurrent());
        voPage.setSize(postPage.getSize());
        voPage.setTotal(postPage.getTotal());
        voPage.setPages(postPage.getPages());
        voPage.setRecords(voList);

        return Result.ok(voPage, "query successfully!");
    }

//    @Override
//    public Result likePost(Long postId) {
//        Posts post = postsMapper.selectPostByPostId(postId);
//        if (post == null) {
//            return Result.fail("not found post！");
//        }else {
//            postsMapper.incrementLikes(postId);
//            return Result.ok("like successfully！");
//        }}

}




