package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>购买商品信息</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/17 上午9:13
 * @since V1.0
 */
@Data
public class BuyGoodsForm {

    @ApiModelProperty(notes = "用户ID")
    @NotNull
    private Long userId;

    @ApiModelProperty(notes = "商品ID")
    @NotNull
    private Long goodsId;

    @ApiModelProperty(notes = "付款价钱")
    @NotNull
    private Double payPrice;
}
