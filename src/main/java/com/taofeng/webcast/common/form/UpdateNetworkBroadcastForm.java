package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>更新节目清单</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午2:14
 * @since V1.0
 */
@Data
public class UpdateNetworkBroadcastForm {

    @ApiModelProperty(notes = "节目类型详情ID")
    @NotNull
    private Long networkBroadcastTypeDetailId;

    @ApiModelProperty(notes = "节目类型")
    @NotNull
    private Long typeId;

    @ApiModelProperty(notes = "节目的详细名称")
    @NotNull
    private String typeDetailName;

    @ApiModelProperty(notes = "节目的详细描述")
    @NotNull
    private String briefIntroduction;
}
