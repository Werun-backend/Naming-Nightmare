package com.werun.posts.server.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.werun.posts.DTO.PageModel;
import com.werun.posts.domain.Label;
import com.werun.posts.mapper.LabelMapper;
import com.werun.posts.response.BaseResponse;
import com.werun.posts.server.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;

public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {
    @Autowired
    public LabelMapper labelMapper;

    /**
     * 新增标签
     *
     * @param labelContext
     * @return
     */
    @Override
    public BaseResponse createLabel(String labelContext) {
        //1. 添加标签内容
        Label label = new Label();
        label.setLabelContext(labelContext);
        //2. 放入库中
        labelMapper.insert(label);
        return BaseResponse.success("成功创建标签！");
    }

    /**
     * 查询所有标签
     *
     * @param pageModel
     * @return
     */
    @Override
    public BaseResponse readAllLabels(PageModel pageModel) {
        QueryWrapper<Label> wrapper = new QueryWrapper<>();
        Page<Label> page = new Page<>(pageModel.getPageNo(), pageModel.getPageSize());
        IPage<Label> pageList = this.page(page, wrapper);

        //返回分页结果
        return BaseResponse.success(pageList,"query successfully!");
    }
}
