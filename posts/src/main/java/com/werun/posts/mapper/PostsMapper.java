package com.werun.posts.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.werun.posts.domain.Posts;
import org.apache.ibatis.annotations.Param;

public interface PostsMapper extends BaseMapper<Posts> {
    /**
     * 插入新用户
     *
     * @param post 帖子对象
     * @return 影响的行数
     */
    public int insertPost(Posts post);

    /**
     * 根据帖子Id查询帖子
     *
     * @param postId
     * @return 帖子信息
     */
    public Posts selectPostByPostId(@Param("postId") Long postId);

    /**
     * 根据用户id查询帖子
     *
     * @param userId 用户id
     * @return 帖子信息
     */
    public Posts selectPostByUserId(@Param("userId") Long userId);



}
