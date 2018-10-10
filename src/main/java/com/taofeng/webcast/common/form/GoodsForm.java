package com.taofeng.webcast.common.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>商品表单</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/12 下午5:00
 * @since V1.0
 */
@Data
public class GoodsForm extends PageForm{

    @ApiModelProperty(notes = "商品编号")
    private String goodsNo;

    @ApiModelProperty(notes = "商品名称")
    private String goodsName;

    @ApiModelProperty(notes = "商品前置价格")
    private Double upPrice;

    @ApiModelProperty(notes = "商品后置价格")
    private Double underPrice;

    @ApiModelProperty(notes = "上架日期")
    private String date;
}
