package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>添加投诉列表</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午1:06
 * @since V1.0
 */
@Data
public class AddComplainForm {

    @ApiModelProperty(notes = "投诉姓名")
    @NotNull
    private String userName;

    @ApiModelProperty(notes = "被投诉人姓名")
    @NotNull
    private String defendantUserName;

    @ApiModelProperty(notes = "原因")
    @NotNull
    private String reason;
}
