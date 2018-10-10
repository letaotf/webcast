package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>修改投诉</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午1:07
 * @since V1.0
 */
@Data
public class UpdateComplainForm {

    @ApiModelProperty(notes = "投诉id")
    @NotNull
    private Long complainId;

    @ApiModelProperty(notes = "原因")
    @NotNull
    private String reason;

    @ApiModelProperty(notes = "投诉等级")
    @NotNull
    private Integer complainRank;
}
