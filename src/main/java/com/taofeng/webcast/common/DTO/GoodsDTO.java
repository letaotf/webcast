package com.taofeng.webcast.common.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>商品列表</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/12 下午5:07
 * @since V1.0
 */
@Data
public class GoodsDTO {

    @ApiModelProperty(notes = "商品ID")
    @NotNull
    private Long goodsId;

    @ApiModelProperty(notes = "商品编号")
    @NotNull
    private String goodsNo;

    @ApiModelProperty(notes = "商品名称")
    @NotNull
    private String goodsName;

    @ApiModelProperty(notes = "商品在线状态")
    @NotNull
    private Integer goodsOnlineStatus;

    @ApiModelProperty(notes = "商品价格")
    @NotNull
    private Double goodsPrice;

    @ApiModelProperty(notes = "图片路径")
    @NotNull
    private String imgPath;

    @ApiModelProperty(notes = "上架时间")
    @NotNull
    private String gmtModified;
}
