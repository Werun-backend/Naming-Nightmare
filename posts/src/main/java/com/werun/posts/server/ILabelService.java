package com.werun.posts.server;

import com.werun.posts.DTO.PageModel;
import com.werun.posts.response.BaseResponse;

public interface ILabelService {
    /**
     * 创建标签
     * @param labelContext
     * @return
     */
    public BaseResponse createLabel(String labelContext);

    /**
     * 查询所有标签
     *
     * @param pageModel
     * @return
     */
    public BaseResponse readAllLabels(PageModel pageModel);
}
