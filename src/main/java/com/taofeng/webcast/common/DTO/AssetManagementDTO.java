package com.taofeng.webcast.common.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午2:48
 * @since V1.0
 */
@Data
public class AssetManagementDTO {

    @ApiModelProperty(notes = "资产ID")
    private Long assetId;

    @ApiModelProperty(notes = "用户ID")
    private String userNo;

    @ApiModelProperty(notes = "资产编号")
    private String assetNo;

    @ApiModelProperty(notes = "鱼丸数量")
    private Integer fishBallNum;

    @ApiModelProperty(notes = "鱼丸的价值")
    private Double fishBallValue;

    @ApiModelProperty(notes = "鲜花的数量")
    private Integer flowerNum;

    @ApiModelProperty(notes = "鲜花的价值")
    private Double flowerValue;

     @ApiModelProperty(notes = "火箭的数量")
    private Integer rocketNum;

    @ApiModelProperty(notes = "火箭的价值")
    private Double rocketValue;
}
