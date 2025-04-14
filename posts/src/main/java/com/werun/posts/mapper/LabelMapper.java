package com.werun.posts.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.werun.posts.domain.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelMapper extends BaseMapper<Label> {

    /**
     * 用标签内容查询到标签
     *
     * @param LabelContent
     * @return
     */
    @Select("Select * From label Where label_content Like #{LabelContent}")
    public Label selectLabelContentByContent(@Param("label_content") String LabelContent);

}
