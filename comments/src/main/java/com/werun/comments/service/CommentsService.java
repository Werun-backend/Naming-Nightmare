package com.werun.comments.service;

import com.werun.comments.VO.CommentsVO;

import java.util.List;

public interface CommentsService {
    void replyPost(Long postId, String content);

    void likePost(Long commentId);

    void replyComment(Long postId, Integer parentId, String content);

    void deleteComment(Long commentId);

    List<CommentsVO> selectComments(Long postId);

    List<CommentsVO> selectByLike(Long postId);

    List<CommentsVO> selectByTime(Long postId);
}
