package com.werun.posts.DTO;

import java.time.LocalDateTime;

/**
 * 帖子数据传输对象
 */
public class PostDTO {
    private String title;
    private String content;
    private Long labelId;
    private boolean scheduled;
    private LocalDateTime scheduledTime;


    public PostDTO() {
    }

    public PostDTO(String title, String content, Long labelId, boolean scheduled, LocalDateTime scheduledTime) {
        this.title = title;
        this.content = content;
        this.labelId = labelId;
        this.scheduled = scheduled;
        this.scheduledTime = scheduledTime;
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
        return "PostDTO{title = " + title + ", content = " + content + ", labelId = " + labelId + ", scheduled = " + scheduled + ", scheduledTime = " + scheduledTime + "}";
    }
}
