package com.werun.posts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.werun.common.core.request.Result;
import com.werun.posts.DTO.PageModel;
import com.werun.posts.domain.Label;
import com.werun.posts.mapper.LabelMapper;
import com.werun.posts.service.ILabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {
    @Autowired
    public LabelMapper labelMapper;

    /**
     * 新增标签
     *
     * @param labelContent
     * @return
     */
    @Override
    public Result createLabel(String labelContent) {
        //1. 添加标签内容
        Label label = new Label();
        label.setLabelContent(labelContent);
        //2. 放入库中
        labelMapper.insert(label);
        return Result.ok("成功创建标签！");
    }

    /**
     * 查询所有标签
     *
     * @param pageModel
     * @return
     */
    @Override
    public Result readAllLabels(PageModel pageModel) {
        QueryWrapper<Label> wrapper = new QueryWrapper<>();
        Page<Label> page = new Page<>(pageModel.getPageNo(), pageModel.getPageSize());
        IPage<Label> pageList = this.page(page, wrapper);

        //返回分页结果
        return Result.ok(pageList,"query successfully!");
    }
}
