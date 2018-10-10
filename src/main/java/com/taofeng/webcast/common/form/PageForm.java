package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>页数表单</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/12 下午4:09
 * @since V1.0
 */
@Data
public class PageForm {

    @ApiModelProperty(notes = "页数码",required = true)
    private Integer pageNo;

    @ApiModelProperty(notes = "页的大小",required = true)
    private Integer pageSize;
}
