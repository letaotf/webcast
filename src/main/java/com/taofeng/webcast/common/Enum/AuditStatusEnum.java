package com.taofeng.webcast.common.Enum;

/**
 * <p>审核状态的枚举</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/12 下午4:24
 * @since V1.0
 */
public enum AuditStatusEnum {

    AUDIT_NOVALID(0,"不通过"),
    AUDIT_VALID(1,"通过"),;

    private Integer code;
    private String meg;

    AuditStatusEnum(Integer code,String meg){
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
