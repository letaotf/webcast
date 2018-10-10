package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>资产详情表单</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/15 下午8:44
 * @since V1.0
 */
@Data
public class AssetDetailsForm extends PageForm {

    @ApiModelProperty(notes = "资产id",required = true)
    @NotNull
    private Long assetId;

    @ApiModelProperty(notes = "年id")
    private Long yearId;

    @ApiModelProperty(notes = "月id")
    private Long monthId;

    @ApiModelProperty(notes = "日id")
    private Long dayId;
}
