package com.werun.posts.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.werun.posts.domain.Posts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsMapper extends BaseMapper<Posts> {

    /**
     * 根据帖子Id查询帖子
     *
     * @param postId
     * @return 帖子信息
     */
    @Select("SELECT * FROM posts WHERE post_id = #{postId}")
    public Posts selectPostByPostId(@Param("postId") Long postId);

    /**
     * 根据用户id查询帖子
     *
     * @param userId 用户id
     * @return 帖子信息
     */
    @Select("SELECT * FROM posts WHERE author_id = #{userId}")
    public Posts selectPostByUserId(@Param("userId") Long userId);

    /**
     * 点赞：将帖子点赞数加一
     *
     * @param postId 帖子ID
     * @return 受影响的行数
     */
    @Update("UPDATE posts SET number_of_likes = number_of_likes + 1 WHERE post_id = #{postId}")
    int incrementLikes(@Param("postId") Long postId);

}
