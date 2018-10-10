package com.taofeng.webcast.common.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>资产详情</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午2:51
 * @since V1.0
 */
@Data
public class AssetManagementDetailDTO {

    @ApiModelProperty(notes = "资产详细id")
    private Long assetDetailId;

    @ApiModelProperty(notes = "资产Id")
    private Long assetId;

    @ApiModelProperty(notes = "day描述id")
    private Long dayDescription;

    @ApiModelProperty(notes = "month描述id")
    private Long monthDescription;

    @ApiModelProperty(notes = "year描述id")
    private Long yearDescription;

    @ApiModelProperty(notes = "鱼丸的总价")
    private Double fishBallMoney;

    @ApiModelProperty(notes = "鲜花的总价")
    private Double flowerMoney;

    @ApiModelProperty(notes = "火箭的总价")
    private Double rocketMoney;

    @ApiModelProperty(notes = "总价")
    private Double allMoney;

}
