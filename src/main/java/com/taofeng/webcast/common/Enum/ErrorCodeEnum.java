package com.taofeng.webcast.common.Enum;

/**
 * <p>错误类型描述枚举</p >
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 下午3:23
 * @since V1.0
 */
public enum ErrorCodeEnum {

    SUCCESS("0001","成功"),
    NO_DATA("0002","无数据"),
    PARAM_ERROR("1003", "参数错误"),
    EXCEPTION("9999", "系统异常"),;

    private String errorCode;
    private String errorMeg;

    ErrorCodeEnum(String errorCode,String errorMeg){
        this.errorCode = errorCode;
        this.errorMeg = errorMeg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMeg() {
        return errorMeg;
    }

}
