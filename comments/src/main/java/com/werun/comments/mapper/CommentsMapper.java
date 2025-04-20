package com.werun.comments.mapper;

import com.werun.comments.PO.CommentsPO;
import com.werun.common.core.domain.UserPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface CommentsMapper {
    /**
     * 回复帖子
     * @param postId
     * @param userId
     * @param content
     */
    @Insert("insert into comments (post_id, user_id, content, status, create_time) values (#{postId}, #{userId}, #{content}, #{status}, #{createTime})")
    void replyPost(@Param("postId") Long postId, @Param("userId")Long userId, @Param("content")String content, @Param("status")Integer status, @Param("createTime") Date createTime);

    /**

    /**
     * 获取帖子的点赞数
     * @param commentId
     * @return
     */
    @Select("select likes from comments where comment_id = #{commentId}")
    Integer getLikeMount(@Param("commentId") Long commentId);

    /**
     * 点赞帖子
     * @param commentId
     * @param likes
     */
    @Insert("update comments set likes = #{likes} where comment_id = #{commentId}")
    void likePost(@Param("commentId") Long commentId, @Param("likes") Integer likes);

    @Select("select parent_id from comments where comment_id = #{commentId}")
    Integer getParentId(@Param("commentId") Long commentId);

    @Insert("insert into comments (post_id, parent_id, user_id, content, status, create_time) values (#{postId}, #{parentId}, #{userId}, #{content}, #{status}, #{createTime})")
    void ReplyComment(@Param("postId") Long postId, @Param("parentId") Integer parentId, @Param("userId") Long userId,
                      @Param("content") String content, @Param("status") Integer status, @Param("createTime") Date createTime);

    @Select("select user_id from comments where comment_id = #{commentId}")
    Long selectParentUserByCommentId(@Param("commentId") Long commentId);

    @Select("select user_id from comments where comment_id = #{commentId}")
    Long getUserIdByCommentId(@Param("commentId") Long commentId);

    @Select("select post_id from comments where comment_id = #{commentId}")
    Long getPostId(@Param("commentId") Long commentId);

    @Select("select author_id from posts where post_id = #{postId}")
    Long getUserIdByPostId(@Param("postId") Long postId);

    @Update("update comments set status = #{status} where comment_id = #{commentId}")
    void deleteComment(@Param("commentId") Long commentId, @Param("status") Integer status);

    @Select("select * from comments where post_id = #{postId} and status = 0")
    List<CommentsPO> selectAllComments(@Param("postId") Long postId);

    @Select("select visible from posts where post_id = #{postId} ")
    boolean selectPostStatus(@Param("postId") Long postId);

    @Select("select * from comments where post_id = #{postId} and status = 0 order by likes desc")
    List<CommentsPO> selectByLike(@Param("postId") Long postId);

    @Select("select * from comments where post_id = #{postId} and status = 0 order by create_time desc")
    List<CommentsPO> selectByTime(@Param("postId") Long postId);

    /**
    @Select("SELECT" +
            "    (SELECT user_name FROM user WHERE user_id = #{userId}) AS sendName," +
            "    (SELECT user_name FROM user WHERE user_id = #{parentId}) AS parentName;")
    void select(Long postId, Long userId, String content, Integer status);
    */
}
