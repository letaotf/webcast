package com.taofeng.webcast.common.Enum;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午10:20
 * @since V1.0
 */
public enum DealComplainEnum {

    NO_PASS(0,"不通过"),
    PASS(1,"通过"),;

    private Integer code;
    private String meg;

    DealComplainEnum(Integer code,String meg){
        this.code = code;
        this.meg = meg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMeg() {
        return meg;
    }
}
