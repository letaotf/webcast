package com.taofeng.webcast.dao.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BuyGoodsDO {
    /**
     * 购买商品ID
     */
    private Long buyGoodsId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 付款价钱
     */
    private Double payPrice;

    /**
     * 付款状态
     */
    private Integer payStatus;

    /**
     * 使用情况
     */
    private Integer employStatus;

    /**
     * 该条记录的状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

}