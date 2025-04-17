package com.werun.comments.VO;

import com.werun.comments.PO.CommentsPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsVO {
    /**
     * 评论信息
     */
    private CommentsPO commentsPO;
    /**
     * 评论者id
     */
    private Long sendUserId;
    /**
     * 评论者用户名
     */
    private String sendUsername;
    /**
     * 被评论者id
     */
    private Long parentUserId;
    /**
     * 被评论者用户名
     */
    private String parentUsername;

}
