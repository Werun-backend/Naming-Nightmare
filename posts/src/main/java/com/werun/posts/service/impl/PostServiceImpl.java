package com.werun.posts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.werun.common.core.request.Result;
import com.werun.posts.DTO.PageModel;
import com.werun.posts.DTO.PostDTO;
import com.werun.posts.VO.PostVO;
import com.werun.posts.domain.Label;
import com.werun.posts.domain.Posts;
import com.werun.posts.mapper.LabelMapper;
import com.werun.posts.mapper.PostsMapper;
import com.werun.posts.service.IPostService;
import com.werun.posts.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostServiceImpl extends ServiceImpl<PostsMapper, Posts> implements IPostService {
    @Autowired
    public PostsMapper postsMapper;

    @Autowired
    public LabelMapper labelMapper;
    //实现帖子（post）的增删改查

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

        //1.3. 默认不可见
        post.setVisible(true);

        //1.4. 获取前端传来的信息
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setLabelId(postDTO.getLabelId());

        //2. 放入库中
        postsMapper.insert(post);
        return Result.ok("成功创建帖子！");
    }

    /**
     * 删除帖子
     *
     * @return
     */
    @Override
    public Result deletePost(Long postId) {
        //1. 校验身份
        Posts post = postsMapper.selectPostByPostId(postId);
        if (!post.getAuthorId().equals(SecurityUtils.getUserId())) {
            //1.1. 无删除权限
            return Result.fail("Delete failed！");
        }

        //2. 更改状态
        post.setVisible(false);

        //3. 删除成功
        return Result.ok("Delete successfully！");
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
        wrapper.eq("author_id", userId);
        Page<Posts> page = new Page<>(pageModel.getPageNo(), pageModel.getPageSize());
        IPage<Posts> postPage = this.page(page, wrapper);

        //3. 转换为 PostVO 分页对象
        List<PostVO> voList = postPage.getRecords().stream().map(post -> {
            PostVO vo = new PostVO();
            vo.setPostId(post.getPostId());
            vo.setTitle(post.getTitle());
            vo.setAuthorId(post.getAuthorId());
            vo.setContent(post.getContent());
            vo.setCreatedAt(post.getCreatedAt());
            vo.setLabelId(post.getLabelId());
            vo.setNumberOfComments(post.getNumberOfComments());
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
     * 用标签查询帖子
     *
     * @param LabelContent
     * @return
     */
    @Override
    public Result readPostByLabel(String LabelContent, PageModel pageModel) {
        //1. 模糊查询到帖子id
        Long labelId = labelMapper.selectLabelContentByContent(LabelContent).getLabelId();

        //2. 查询帖子
        QueryWrapper<Posts> wrapper = new QueryWrapper<>();
        wrapper.eq("visible", true);
        wrapper.eq("label_id", labelId);
        Page<Posts> page = new Page<>(pageModel.getPageNo(), pageModel.getPageSize());
        IPage<Posts> postPage = this.page(page, wrapper);

        //3. 转换为 PostVO 分页对象
        List<PostVO> voList = postPage.getRecords().stream().map(post -> {
            PostVO vo = new PostVO();
            vo.setPostId(post.getPostId());
            vo.setTitle(post.getTitle());
            vo.setAuthorId(post.getAuthorId());
            vo.setContent(post.getContent());
            vo.setCreatedAt(post.getCreatedAt());
            vo.setLabelId(post.getLabelId());
            vo.setNumberOfComments(post.getNumberOfComments());
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
     * 用帖子内容查询帖子
     *
     * @param PostContent
     * @return
     */
    @Override
    public Result readPostByContent(String PostContent, PageModel pageModel) {
        //1. 查询帖子
        QueryWrapper<Posts> wrapper = new QueryWrapper<>();
        wrapper.eq("visible", true);
        wrapper.like("content", PostContent);
        Page<Posts> page = new Page<>(pageModel.getPageNo(), pageModel.getPageSize());
        IPage<Posts> postPage = this.page(page, wrapper);

        //2. 转换为 PostVO 分页对象
        List<PostVO> voList = postPage.getRecords().stream().map(post -> {
            PostVO vo = new PostVO();
            vo.setPostId(post.getPostId());
            vo.setTitle(post.getTitle());
            vo.setAuthorId(post.getAuthorId());
            vo.setContent(post.getContent());
            vo.setCreatedAt(post.getCreatedAt());
            vo.setLabelId(post.getLabelId());
            vo.setNumberOfComments(post.getNumberOfComments());
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
     * 编辑帖子
     *
     * @param postId
     * @param typeName
     * @param newParam
     * @return
     */
    @Override
    public Result updatePost(Long postId, String typeName, String newParam) {
        //1. 查询到原贴
        Posts post = postsMapper.selectPostByPostId(postId);
        if (!post.isVisible()) {
            return Result.fail("not found");
        }

        //2. 编辑帖子
        if (typeName.equals("title")) {
            post.setTitle(newParam);
        }
        if (typeName.equals("content")) {
            post.setContent(newParam);
        }
        if (typeName.equals("label_id")) {
            post.setLabelId(Long.valueOf(newParam));
        }
        postsMapper.updateById(post);

        //3. 提示编辑成功
        return Result.ok(post, "update successfully!");
    }

    /**
     * 根据postId查询帖子
     *
     * @param postId
     * @return
     */
    @Override
    public Result readPostByPostId(Long postId) {
        //1. 查询贴子
        Posts post = postsMapper.selectPostByPostId(postId);
        if (!post.isVisible()) {
            return Result.fail("not found");
        }

        //2. 生成视图对象
        PostVO postVO = new PostVO();
        postVO.setPostId(post.getPostId());
        postVO.setTitle(post.getTitle());
        postVO.setAuthorId(post.getAuthorId());
        postVO.setContent(post.getContent());
        postVO.setCreatedAt(post.getCreatedAt());
        postVO.setLabelId(post.getLabelId());
        postVO.setNumberOfComments(post.getNumberOfComments());

        //3. 查询成功
        return Result.ok(postVO, "query successfully");
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

        //2. 分页展示发表1~5分钟内的所有帖子
        QueryWrapper<Posts> wrapper = new QueryWrapper<>();
        wrapper.eq("visible", true);
        wrapper.between("create_at", now.plusMinutes(1), now.plusMinutes(5));
        Page<Posts> page = new Page<>(pageModel.getPageNo(), pageModel.getPageSize());
        IPage<Posts> postPage = this.page(page, wrapper);

        //3. 转换为 PostVO 分页对象
        List<PostVO> voList = postPage.getRecords().stream().map(post -> {
            PostVO vo = new PostVO();
            vo.setPostId(post.getPostId());
            vo.setTitle(post.getTitle());
            vo.setAuthorId(post.getAuthorId());
            vo.setContent(post.getContent());
            vo.setCreatedAt(post.getCreatedAt());
            vo.setLabelId(post.getLabelId());
            vo.setNumberOfComments(post.getNumberOfComments());
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

}




