package com.werun.posts.domain;

public class Label {
    private Long LabelId;
    private String labelContent;


    public Label() {
    }

    public Label(Long LabelId, String labelContent) {
        this.LabelId = LabelId;
        this.labelContent = labelContent;
    }

    /**
     * 获取
     * @return LabelId
     */
    public Long getLabelId() {
        return LabelId;
    }

    /**
     * 设置
     * @param LabelId
     */
    public void setLabelId(Long LabelId) {
        this.LabelId = LabelId;
    }

    /**
     * 获取
     * @return labelContent
     */
    public String getLabelContent() {
        return labelContent;
    }

    /**
     * 设置
     * @param labelContent
     */
    public void setLabelContent(String labelContent) {
        this.labelContent = labelContent;
    }

    public String toString() {
        return "Label{LabelId = " + LabelId + ", labelContent = " + labelContent + "}";
    }
}
