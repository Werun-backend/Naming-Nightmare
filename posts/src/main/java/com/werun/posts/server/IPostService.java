package com.werun.posts.server;

import com.baomidou.mybatisplus.extension.service.IService;
import com.werun.posts.DTO.PageModel;
import com.werun.posts.DTO.PostDTO;
import com.werun.posts.domain.Posts;
import com.werun.posts.response.BaseResponse;

public interface IPostService extends IService<Posts> {
    /**
     * 创建帖子
     * @param postDTO
     * @return
     */
    public BaseResponse createPost(PostDTO postDTO);

    /**
     * 删除帖子（假删除）
     *
     * @param postId
     * @return
     */
    public BaseResponse deletePost(Long postId);

    /**
     * 查询个人帖子
     *
      * @param pageModel
     * @return
     */
    public BaseResponse readPostByAuthor(PageModel pageModel);

    /**
     * 用帖子id查询帖子
     *
     * @return
     */
    public BaseResponse readPostByPostId(Long postId);

    /**
     * 编辑帖子
     *
     * @param postId
     * @return
     */
    public BaseResponse updatePost(Long postId,PostDTO postDTO);
}
