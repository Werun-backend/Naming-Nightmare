package com.werun.posts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;
@Schema(name = "CreatePostRequest", description = "创建帖子请求体")
public class CreatePostRequest {

        @Schema(description = "帖子内容", implementation = PostDTO.class)
        private PostDTO post;

        @Schema(description = "帖子图片", type = "string", format = "binary")
        private MultipartFile picture;

        // Getter & Setter
        public PostDTO getPost() {
            return post;
        }

        public void setPost(PostDTO post) {
            this.post = post;
        }

        public MultipartFile getPicture() {
            return picture;
        }

        public void setPicture(MultipartFile picture) {
            this.picture = picture;
        }
    }


