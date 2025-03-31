package com.werun.posts.server.iml;

import com.werun.common.core.context.SecurityContextHolder;
import com.werun.posts.DTO.PostDTO;
import com.werun.posts.domain.Posts;
import com.werun.posts.mapper.PostsMapper;
import com.werun.posts.response.BaseResponse;
import com.werun.posts.server.IPostService;
import com.werun.posts.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class PostService implements IPostService {
    @Autowired
    public PostsMapper postsMapper;
    //实现帖子（post）的增删改查

    /**
     * 创建帖子
     *
     * @param postDTO
     * @return
     */
    @Override
    public BaseResponse createPost(PostDTO postDTO) {
        //1. 完善帖子信息
        Posts post = new Posts();
            //1.1. 获取当前用户信息
            post.setAuthorId(SecurityUtils.getUserId());

            //1.2. 获取当前时间
            LocalDateTime now = LocalDateTime.now();
            post.setCreatedAt(now);

            //1.3. 默认未删除状态
            post.setDeleteStatus(false);

            //1.4. 获取前端传来的信息
            post.setTitle(postDTO.getTitle());
            post.setContent(postDTO.getContent());
            post.setLabelId(postDTO.getLabelId());

        //2. 放入库中
        postsMapper.insertPost(post);
        return BaseResponse.success("成功创建帖子！");
    }

    /**
     * 删除帖子
     *
     * @return
     */
    @Override
    public BaseResponse deletePost(Long postId){
        //1. 校验身份
        Posts post = postsMapper.selectPostByPostId(postId);
        if(post.getAuthorId()==SecurityUtils.getUserId()){
            //1.1. 更改状态
            post.setDeleteStatus(true);
            //1.2. 删除成功
            return BaseResponse.success("删除成功！");
        }
        //2. 无删除权限
        return BaseResponse.error("删除失败！");
    }

}


