package com.taofeng.webcast.common.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>购买商品记录DTO</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/11 下午3:36
 * @since V1.0
 */
@Data
public class BuyGoodsRecordDTO {

    @ApiModelProperty(notes = "购买商品ID")
    private Long buyGoodsId;

    @ApiModelProperty(notes = "用户ID")
    private Long userId;

    @ApiModelProperty(notes = "商品ID")
    private Long goodsId;

    @ApiModelProperty(notes = "商品名称")
    private String goodsName;

    @ApiModelProperty(notes = "付款价钱")
    private Double payPrice;

    @ApiModelProperty(notes = "使用情况")
    private Integer employStatus;

    @ApiModelProperty(notes = "使用情况的中文描述")
    private String employStatusDes;

    @ApiModelProperty(notes = "购买时间")
    private String gmtCreate;

}
