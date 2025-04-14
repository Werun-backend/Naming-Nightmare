package com.werun.posts.service;

import com.werun.common.core.request.Result;
import com.werun.posts.DTO.PageModel;

public interface ILabelService {
    /**
     * 创建标签
     * @param labelContext
     * @return
     */
    public Result createLabel(String labelContext);

    /**
     * 查询所有标签
     *
     * @param pageModel
     * @return
     */
    public Result readAllLabels(PageModel pageModel);
}
