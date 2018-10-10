package com.taofeng.webcast.common.Enum;

/**
 * <p>商品使用情况的枚举</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/5/11 下午3:40
 * @since V1.0
 */
public enum EmployStatusEnum {

    UESD(1,"使用"),
    UNUSED(0,"未使用"),;

    private Integer code;
    private String meg;

    EmployStatusEnum(Integer code,String meg){
        this.code = code;
        this.meg = meg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMeg() {
        return meg;
    }

    /**
     * 根据
     * @param code
     * @return
     */
    public static String getMsgByCode(Integer code){
        EmployStatusEnum[] roles = values();
        for (EmployStatusEnum role : roles){
            if (role.getCode().equals(code)){
                return role.getMeg();
            }
        }
        return null;
    }
}
