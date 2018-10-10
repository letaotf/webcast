package com.taofeng.webcast.common.Enum;

/**
 * <p>性别的枚举</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/20 下午5:50
 * @since V1.0
 */
public enum SexEnum {

    BOY(0,"男"),
    GIRL(1,"女");

    private Integer code;
    private String meg;

    SexEnum(Integer code,String meg){
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
     * 根据code码获得meg
     * @param code
     * @return
     */
    public static String getMegByCode(Integer code){
        SexEnum sexEnum[] = values();
        for(int i=0;i<sexEnum.length;i++){
            if(code.equals(sexEnum[i].code)){
                return sexEnum[i].getMeg();
            }
        }
        return null;
    }

}
