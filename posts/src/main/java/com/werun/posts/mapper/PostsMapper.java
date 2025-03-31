package com.werun.posts.mapper;

import com.werun.posts.domain.Posts;
import org.apache.ibatis.annotations.Param;

public interface PostsMapper{
    /**
     * 插入新用户
     *
     * @param post 用户对象
     * @return 影响的行数
     */
    public int insertPost(Posts post);

    /**
     * 根据帖子Id查询帖子
     *
     * @param postId 用户邮箱(作为用户名)
     * @return 用户信息
     */
    public Posts selectPostByPostId(@Param("postId") Long postId);

}
