package com.werun.posts.domain;

import java.time.LocalDateTime;

public class Posts {
    private Long id;
    private String title;
    private String content;
    private Long authorId;
    private LocalDateTime createdAt;
    // private Date updatedAt;
    private boolean deleteStatus;
    private Long labelId;


    public Posts() {
    }

    public Posts(Long id, String title, String content, Long authorId, LocalDateTime createdAt, boolean deleteStatus, Long labelId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.createdAt = createdAt;
        // this.updatedAt = updatedAt;
        this.deleteStatus = deleteStatus;
        this.labelId = labelId;
    }

    /**
     * 获取
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return author
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * 设置
     * @param author
     */
    public void setAuthorId(Long author) {
        this.authorId = author;
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

//    /**
//     * 获取
//     * @return updatedAt
//     */
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }
//
//    /**
//     * 设置
//     * @param updatedAt
//     */
//    public void setUpdatedAt(Date updatedAt) {
//        this.updatedAt = updatedAt;
//    }

    /**
     * 获取
     * @return status
     */
    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    /**
     * 设置
     * @param status
     */
    public void setDeleteStatus(boolean status) {
        this.deleteStatus = status;
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

}