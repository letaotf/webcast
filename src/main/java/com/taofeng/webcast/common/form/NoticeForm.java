package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>公告管理</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午1:55
 * @since V1.0
 */
@Data
public class NoticeForm {

    @ApiModelProperty(notes = "标题")
    @NotNull
    private String title;

    @ApiModelProperty(notes = "内容")
    @NotNull
    private String content;
}
