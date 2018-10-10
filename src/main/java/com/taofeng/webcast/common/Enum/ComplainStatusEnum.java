package com.taofeng.webcast.common.Enum;

/**
 * <p>投诉人类型</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/11 上午10:26
 * @since V1.0
 */
public enum ComplainStatusEnum {


    PLAINTIFF(0,"投诉人"),
    DEFENDANT(1,"被投诉人"),;


    private Integer code;
    private String meg;

    ComplainStatusEnum(Integer code,String meg){
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
