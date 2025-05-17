package com.werun.posts.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 成功创建标签返回视图对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelCreateVO {
   private String labelName;
}
