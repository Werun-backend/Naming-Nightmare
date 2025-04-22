package com.werun.posts.VO;

import java.time.LocalDateTime;

/**
 * 帖子视图对象
 */
public class PostVO {
    private Long postId;
    private String title;
    private Long authorId;
    private String content;
    private LocalDateTime createdAt;
    private Long numberOfComments;
    private Long labelId;
    private Long numberOfLikes;


    public PostVO() {
    }

    public PostVO(Long postId, String title, Long authorId, String content, LocalDateTime createdAt, Long numberOfComments, Long labelId, Long numberOfLikes) {
        this.postId = postId;
        this.title = title;
        this.authorId = authorId;
        this.content = content;
        this.createdAt = createdAt;
        this.numberOfComments = numberOfComments;
        this.labelId = labelId;
        this.numberOfLikes = numberOfLikes;
    }

    /**
     * 获取
     * @return postId
     */
    public Long getPostId() {
        return postId;
    }

    /**
     * 设置
     * @param postId
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    /**
     * 获取
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取
     * @return authorId
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * 设置
     * @param authorId
     */
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    /**
     * 获取
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取
     * @return createdAt
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置
     * @param createdAt
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取
     * @return numberOfComments
     */
    public Long getNumberOfComments() {
        return numberOfComments;
    }

    /**
     * 设置
     * @param numberOfComments
     */
    public void setNumberOfComments(Long numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    /**
     * 获取
     * @return labelId
     */
    public Long getLabelId() {
        return labelId;
    }

    /**
     * 设置
     * @param labelId
     */
    public void setLabelId(Long labelId) {
        this.labelId = labelId;
    }

    /**
     * 获取
     * @return numberOfLikes
     */
    public Long getNumberOfLikes() {
        return numberOfLikes;
    }

    /**
     * 设置
     * @param numberOfLikes
     */
    public void setNumberOfLikes(Long numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public String toString() {
        return "PostVO{postId = " + postId + ", title = " + title + ", authorId = " + authorId + ", content = " + content + ", createdAt = " + createdAt + ", numberOfComments = " + numberOfComments + ", labelId = " + labelId + ", numberOfLikes = " + numberOfLikes + "}";
    }
}
