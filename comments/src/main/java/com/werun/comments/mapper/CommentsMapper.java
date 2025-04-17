package com.werun.comments.mapper;

import com.werun.comments.PO.CommentsPO;
import com.werun.common.core.domain.UserPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CommentsMapper {
    /**
     * 回复帖子
     * @param postId
     * @param userId
     * @param content
     */
    @Insert("insert into comments (post_id, user_id, content, status) values (#{postId}, #{userId}, #{content}, #{status}})")
    void replyPost(Long postId, Long userId, String content, Integer status);

    /**
     * 获取帖子的点赞数
     * @param postId
     * @return
     */
    @Select("select likes from comments where post_id = #{postId}")
    Integer getLikeMount(Long postId);

    /**
     * 点赞帖子
     * @param postId
     * @param likes
     */
    @Insert("update comments set likes = #{likes} where post_id = #{postId}")
    void likePost(Long postId, Integer likes);

    @Select("select parent_id from comments where comment_id = #{commentId}")
    Integer getParentId(Long commentId);

    @Insert("insert into comments (post_id, parent_id, user_id, content, status) values (#{postId}, #{parentId}, #{userId}, #{content}, #{status})")
    void ReplyComment(Long postId, Integer parentId, Long userId, String content, Integer status);

    @Select("select user_id from comments where comment_id = #{commentId}")
    Long selectParentUserByCommentId(Long commentId);

    @Select("select * from user where user_id = #{userId}")
    UserPO selectUserByUserId(Long userId);

    @Select("select user_id from comments where comment_id = #{commentId}")
    Long getUserIdByCommentId(Long commentId);

    @Select("select post_id from comments where comment_id = #{commentId}")
    Long getPostId(Long commentId);

    @Select("select user_id from comments where post_id = #{postId}")
    Long getUserIdByPostId(Long postId);

    @Update("update comments set status = #{status} where comment_id = #{commentId}")
    void deleteComment(Long commentId, Integer status);

    @Select("select * from comments where post_id = #{postId} and status = 0")
    List<CommentsPO> selectAllComments(Long postId);

    /**
    @Select("SELECT" +
            "    (SELECT user_name FROM user WHERE user_id = #{userId}) AS sendName," +
            "    (SELECT user_name FROM user WHERE user_id = #{parentId}) AS parentName;")
    void select(Long postId, Long userId, String content, Integer status);
    */
}
