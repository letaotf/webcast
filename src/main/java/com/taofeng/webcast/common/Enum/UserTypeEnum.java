package com.taofeng.webcast.common.Enum;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/25 下午6:01
 * @since V1.0
 */
public enum UserTypeEnum {

    USER(0,"普通用户"),
    BROADCAST(1,"网络主播"),
    ADMIN(2,"管理员")
    ;


    private Integer code;
    private String meg;

    UserTypeEnum(Integer code,String meg){
        this.code = code;
        this.meg = meg;
    }

    /**
     * 根据code获得meg
     * @param code
     * @return
     */
    public static String getMessage(Integer code){
        UserTypeEnum userTypeEnum[] = values();

        for(int i=0;i<userTypeEnum.length;i++){
            if(code.equals(userTypeEnum[i].getCode())){
                return userTypeEnum[i].getMeg();
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getMeg() {
        return meg;
    }
}
