package com.werun.comments.PO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsPO {
    /**
     * 评论id
     */
    private Long commentId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 回复评论id
     */
    private Integer parentId;
    /**
     * 点赞数
     */
    private Integer likes;
    /**
     * 评论时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 帖子id
     */
    private Long postId;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 状态码(0:未删除,1:已删除)
     */
    private Integer status;


    /**

    public static List<VO> main(String[] args) {
        List coms1 = mapper.selecltbypostid;
        List coms2 = mapper.selcetusernameByUserId;
        List VOS;
        FOR(ID,A);// n

        for coms2 {
            a;
            if(a.getparentId != null){
                for coms2{
                    b;
                    if (a.getparentId == b.id){
                        new VO;
                        VO.add(a);
                        VO.addPARENTInAME = b.name;
                        VOS.ADD(VO);
                    };
                }
            }
            VO.add(a);
            VOS.ADD(VO);
        }
    }
     */
}
