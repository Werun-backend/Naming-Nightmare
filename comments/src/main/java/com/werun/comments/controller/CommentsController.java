package com.werun.comments.controller;

import com.werun.comments.VO.CommentsVO;
import com.werun.comments.service.CommentsService;
import com.werun.common.core.request.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentsController {
    @Autowired
    private CommentsService commentsService;

    /**
     * 在帖子下面添加评论
     * @param postId
     * @param content
     * @return
     */
    @PostMapping("/replyPost")
    @Operation(summary = "在帖子下面添加评论", description = "在帖子下面添加评论")
    public Result<?> replyPost(@RequestParam Long postId, @RequestParam String content) {
        commentsService.replyPost(postId, content);
        return Result.ok();
    }

    /**
     * 给帖子点赞
     * @param commentId
     * @return
     */
    @PostMapping("/like")
    @Operation(summary = "给评论点赞", description = "给评论点赞")
    public Result<?> likeComment(@RequestParam Long commentId) {
        commentsService.likePost(commentId);
        return Result.ok();
    }

    /**
     * 用户回复其他人评论
     * @Param parentId
     * @param content
     */
    @PostMapping("/replyComment")
    @Operation(summary = "用户回复其他人评论", description = "用户回复其他人评论")
    public Result<?> replyComment(@RequestParam Long postId, @RequestParam Integer parentId, @RequestParam String content) {
        commentsService.replyComment(postId, parentId, content);
        return Result.ok();
    }
    /**
     * 查询帖子下所有评论
     * @Param postId
     * @return
     */
    @GetMapping("/selectComments")
    @Operation(summary = "查询帖子下所有评论", description = "查询帖子下所有评论")
    public Result<?> selectComments(@RequestParam Long postId) {
        List<CommentsVO> commentsVOS = commentsService.selectComments(postId);
        return Result.ok(commentsVOS);

    }

    /**
     * 删除评论
     * @Param commentId
     * @return
     */
    @PostMapping("/delete")
    @Operation(summary = "删除评论", description = "删除评论")
    public Result<?> deleteComment(@RequestParam Long commentId) {
        commentsService.deleteComment(commentId);
        return Result.ok();
    }

    /**
     * 根据点赞数排序
     * @Param postId
     * @return
     */
    @GetMapping("/selectByLike")
    @Operation(summary = "根据点赞数排序", description = "根据点赞数排序")
    public Result<?> selectByLike(@RequestParam Long postId) {
        List<CommentsVO> commentsVOS = commentsService.selectByLike(postId);
        return Result.ok(commentsVOS);
    }

    /**
     * 根据发表时间排序
     * @Param postId
     * @return
     */
    @GetMapping("/selectByTime")
    @Operation(summary = "根据发表时间排序", description = "根据发表时间排序")
    public Result<?> selectByTime(@RequestParam Long postId) {
        List<CommentsVO> commentsVOS = commentsService.selectByTime(postId);
        return Result.ok(commentsVOS);
    }
}
