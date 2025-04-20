package com.werun.comments.service.impl;

import com.werun.comments.PO.CommentsPO;
import com.werun.comments.VO.CommentsVO;
import com.werun.comments.mapper.CommentsMapper;
import com.werun.comments.service.CommentsService;
import com.werun.comments.utils.SecurityUtils;
import com.werun.common.core.domain.UserPO;
import com.werun.common.core.request.Result;
import com.werun.common.openFeign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public void replyPost(Long postId, String content) {
        Long userId = SecurityUtils.getUserId();
        Integer status = 0;
        Date createTime = new Date();
        commentsMapper.replyPost(postId,userId,content,status,createTime);
    }

    @Override
    public void likePost(Long commentId) {
        Integer parentId = commentsMapper.getParentId(commentId);
        if (parentId == 0) {
            Integer like = commentsMapper.getLikeMount(commentId);
            Integer likes = like + 1;
            commentsMapper.likePost(commentId,likes);
        }else {
            log.info("无需点赞");
        }

    }

    @Override
    public void replyComment(Long postId, Integer parentId, String content) {
        Long userId = SecurityUtils.getUserId();
        Integer status = 0;
        Date createTime = new Date();
        commentsMapper.ReplyComment(postId, parentId, userId, content, status, createTime);
        /**
        Long commentId = Long.valueOf(parentId);
        Long parentUserId = commentsMapper.selectParentUserByCommentId(commentId);
        Long currentUserId = commentsMapper.selectUserByUserId(userId);
        commentsMapper.addReplyedComment(parentUserId,currentUserId,content);
         */

    }

    @Override
    public List<CommentsVO> selectComments(Long postId) {
        boolean result = commentsMapper.selectPostStatus(postId);
        if (result) {
            List<CommentsPO> commentList = commentsMapper.selectAllComments(postId);
            List<CommentsVO> commentsVOS = getCommentsVOByCommentsPO(commentList);
            return commentsVOS;
        }else {
            log.info("帖子已被删除");
            return null;
        }

    }

    @Override
    public List<CommentsVO> selectByLike(Long postId) {
        boolean result = commentsMapper.selectPostStatus(postId);
        if (result) {
            List<CommentsPO> commentList = commentsMapper.selectByLike(postId);
            List<CommentsVO> commentsVOS = getCommentsVOByCommentsPO(commentList);
            return commentsVOS;
        }else {
            log.info("帖子已被删除");
            return null;
        }

    }

    @Override
    public List<CommentsVO> selectByTime(Long postId) {
        boolean result = commentsMapper.selectPostStatus(postId);
        if (result) {
            List<CommentsPO> commentList = commentsMapper.selectByTime(postId);
            List<CommentsVO> commentsVOS = getCommentsVOByCommentsPO(commentList);
            return commentsVOS;
        }else {
            log.info("帖子已被删除");
            return null;
        }
    }

    @Override
    public void deleteComment(Long commentId) {
        //当前用户id
        Long userId1 = SecurityUtils.getUserId();
        //该评论归属用户id
        Long userId2 = commentsMapper.getUserIdByCommentId(commentId);
        //评论所属帖子的发表用户id
        Long postId = commentsMapper.getPostId(commentId);
        Long userId3 = commentsMapper.getUserIdByPostId(postId);
        if (userId1.equals(userId2) || userId1.equals(userId3)) {
            Integer status = 1;
            commentsMapper.deleteComment(commentId, status);
        }else {
            throw new RuntimeException("权限不足");
        }

    }

    public List<CommentsVO> getCommentsVOByCommentsPO(List<CommentsPO> commentList) {
        List<CommentsVO> commentsVOS = new ArrayList<>();
        for (int i = 0; i < commentList.size(); i++) {
            CommentsVO commentsVO = new CommentsVO();
            Long sendUserId = commentList.get(i).getUserId();
            Result<UserPO> result1 = userFeignClient.selectUserMessage(sendUserId);
            if (result1.getCode() != 200){
                continue;
            }
            UserPO userPO1 = result1.getData();
            commentsVO.setCommentsPO(commentList.get(i));
            commentsVO.setSendUsername(userPO1.getNickName());
            commentsVO.setSendUserId(userPO1.getUserId());

            Integer parentId = commentList.get(i).getParentId();
            if (parentId == 0) {
                commentsVOS.add(commentsVO);
                continue;
            }
            Long commentId = Long.valueOf(parentId);
            Long parentUserId = 0L;
            for (int i1 = 0; i1 < commentList.size(); i1++) {
                if (commentList.get(i1).getCommentId().equals(commentId)) {
                    parentUserId = commentList.get(i1).getUserId();
                }
            }

            Result<UserPO> result2 = userFeignClient.selectUserMessage(parentUserId);
            if (result2.getCode() != 200){
                continue;
            }
            UserPO userPO2 = result2.getData();
            commentsVO.setParentUsername(userPO2.getNickName());
            commentsVO.setParentUserId(userPO2.getUserId());
            commentsVOS.add(commentsVO);

        }
        return commentsVOS;
    }

}
