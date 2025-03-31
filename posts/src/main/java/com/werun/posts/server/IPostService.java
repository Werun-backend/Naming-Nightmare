package com.werun.posts.server;

import com.werun.posts.DTO.PostDTO;
import com.werun.posts.response.BaseResponse;

public interface IPostService {
    //1. 创建帖子
    public BaseResponse createPost(PostDTO postDTO);
    //2. 删除帖子（假删除）
    public BaseResponse deletePost(Long postId);
}
