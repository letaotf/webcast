package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>处理投诉等级</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午1:10
 * @since V1.0
 */
@Data
public class DealComplainForm {

    @ApiModelProperty(notes = "投诉id")
    @NotNull
    private Long complainId;

    @ApiModelProperty(notes = "投诉等级")
    @NotNull
    private Integer operatorType;
}
