package com.werun.posts.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 编辑帖子DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateDTO {
    private Long postId;
    private String title;
    private String content;
    private Long labelId;
}
