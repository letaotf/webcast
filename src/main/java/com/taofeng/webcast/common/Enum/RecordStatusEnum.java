package com.taofeng.webcast.common.Enum;

/**
 * <p>查看数据是否有效</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/28 下午4:57
 * @since V1.0
 */
public enum RecordStatusEnum {

    VALID(1,"有效"),
    NO_VALID(0,"无效");

    private Integer code;
    private String name;
    RecordStatusEnum(Integer code,String name){
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
