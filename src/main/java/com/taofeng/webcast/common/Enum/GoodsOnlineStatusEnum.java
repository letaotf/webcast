package com.taofeng.webcast.common.Enum;

/**
 * <p>商品在线情况</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/12 下午4:56
 * @since V1.0
 */
public enum GoodsOnlineStatusEnum {

    ON_ONLINE(0,"下架"),
    ONLINE(1,"上架"),;

    private Integer code;
    private String meg;

    GoodsOnlineStatusEnum(Integer code,String meg){
        this.code = code ;
        this.meg = meg ;
    }

    public Integer getCode() {
        return code;
    }

    public String getMeg() {
        return meg;
    }
}
