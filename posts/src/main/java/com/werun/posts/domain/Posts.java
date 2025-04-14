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
    private boolean visible;
    private Long labelId;
    private Long numberOfComments;
    private boolean scheduled;
    private LocalDateTime scheduledTime;


    public Posts() {
    }

    public Posts(Long postId, String title, String content, Long authorId, LocalDateTime createdAt, boolean visible, Long labelId, Long numberOfComments, boolean scheduled, LocalDateTime scheduledTime) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.createdAt = createdAt;
        this.visible = visible;
        this.labelId = labelId;
        this.numberOfComments = numberOfComments;
        this.scheduled = scheduled;
        this.scheduledTime = scheduledTime;
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
     * @return visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * 设置
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
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

    /**
     * 获取
     * @return scheduled
     */
    public boolean isScheduled() {
        return scheduled;
    }

    /**
     * 设置
     * @param scheduled
     */
    public void setScheduled(boolean scheduled) {
        this.scheduled = scheduled;
    }

    /**
     * 获取
     * @return scheduledTime
     */
    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    /**
     * 设置
     * @param scheduledTime
     */
    public void setScheduledTime(LocalDateTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String toString() {
        return "Posts{postId = " + postId + ", title = " + title + ", content = " + content + ", authorId = " + authorId + ", createdAt = " + createdAt + ", visible = " + visible + ", labelId = " + labelId + ", numberOfComments = " + numberOfComments + ", scheduled = " + scheduled + ", scheduledTime = " + scheduledTime + "}";
    }
}