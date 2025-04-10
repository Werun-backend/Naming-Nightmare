package com.werun.posts.domain;

import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

public class Posts {
    @TableId
    private Long postId;
    private String title;
    private String content;
    private Long authorId;
    private LocalDateTime createdAt;
    // private Date updatedAt;
    private boolean deleteStatus;
    private Long labelId;
    private Long numberOfComments;


    public Posts() {
    }

    public Posts(Long postId, String title, String content, Long authorId, LocalDateTime createdAt, boolean deleteStatus, Long labelId, Long numberOfComments) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.createdAt = createdAt;
        this.deleteStatus = deleteStatus;
        this.labelId = labelId;
        this.numberOfComments = numberOfComments;
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
     * @return deleteStatus
     */
    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置
     * @param deleteStatus
     */
    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
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

    public String toString() {
        return "Posts{postId = " + postId + ", title = " + title + ", content = " + content + ", authorId = " + authorId + ", createdAt = " + createdAt + ", deleteStatus = " + deleteStatus + ", labelId = " + labelId + ", numberOfComments = " + numberOfComments + "}";
    }
}