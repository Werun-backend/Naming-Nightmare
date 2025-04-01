package com.werun.posts.domain;

public class Label {
    private Long id;
    private String labelContext;


    public Label() {
    }

    public Label(Long id, String labelContext) {
        this.id = id;
        this.labelContext = labelContext;
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
     * @return labelContext
     */
    public String getLabelContext() {
        return labelContext;
    }

    /**
     * 设置
     * @param labelContext
     */
    public void setLabelContext(String labelContext) {
        this.labelContext = labelContext;
    }

    public String toString() {
        return "Label{id = " + id + ", labelContext = " + labelContext + "}";
    }
}
