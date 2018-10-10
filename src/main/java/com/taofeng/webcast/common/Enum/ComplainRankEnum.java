package com.taofeng.webcast.common.Enum;

/**
 * <p>描述</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/3/13 下午10:24
 * @since V1.0
 */
public enum ComplainRankEnum {

    First_CLASS(1,"一等"),
    SECOND_CLASS(2,"二等"),
    THIRD_CLASS(3,"三等");

    private Integer code;
    private String meg;

    ComplainRankEnum(Integer code,String meg){
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
        ComplainRankEnum[] roles = values();
        for (ComplainRankEnum role : roles){
            if (role.getCode().equals(code)){
                return role.getMeg();
            }
        }
        return null;
    }

    /**
     * 根据验code 获取枚举对象
     * @param code
     * @return
     */
    public static ComplainRankEnum valueOf(Integer code){
        for (ComplainRankEnum statusEnum: ComplainRankEnum.values()) {
            if(statusEnum.getCode().equals(code)){
                return statusEnum;
            }
        }
        return null;
    }



}
