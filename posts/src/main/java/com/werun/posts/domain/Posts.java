package com.werun.posts.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Posts {
    @TableId
    private Long postId;
    private String title;
    private String content;
    private byte[] picture;
    private Long authorId;
    private LocalDateTime createdAt;
    private boolean visible;
    private Long labelId;
    private Long numberOfComments;
    private Long numberOfLikes;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime scheduledTime;
    private boolean scheduled;





}