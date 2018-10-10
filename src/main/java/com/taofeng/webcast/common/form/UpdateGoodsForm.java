package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>更新商品的表单</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 上午12:02
 * @since V1.0
 */
@Data
public class UpdateGoodsForm {

    @ApiModelProperty(notes = "商品Id",required = true)
    @NotNull
    private Long goodsId;

    @ApiModelProperty(notes = "商品名称",required = true)
    @NotNull
    private String goodsName;

    @ApiModelProperty(notes = "商品价格",required = true)
    @NotNull
    private Double goodsPrice;

    @ApiModelProperty(notes = "图片路径")
    private String imgPath;
}
