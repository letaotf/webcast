package com.taofeng.webcast.dao.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GoodsDO {

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品编号
     */
    private String goodsNo;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品在线状态
     */
    private Integer goodsOnlineStatus;

    /**
     * 商品价格
     */
    private Double goodsPrice;

    /**
     * 图片路径
     */
    private String imgPath;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;
}