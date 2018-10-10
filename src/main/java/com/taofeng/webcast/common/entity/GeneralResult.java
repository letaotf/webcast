package com.taofeng.webcast.common.entity;

import com.taofeng.webcast.common.Enum.ErrorCodeEnum;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>传递给前端的数剧对象</p>
 *
 * @author: 乐陶（letao@maihaoche.com）
 * @date: 2018/2/25 下午3:26
 * @since V1.0
 */
public class GeneralResult<T> implements Serializable{

    /**
     * 是否成功
     */
    private boolean success = false;

    /**
     * 返回结果码
     */
    private String code;

    /**
     * 返回结果描述
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 补充数据
     */
    private Map<String, Object> extraInfo;

    public static <T> GeneralResult<T> success(T data){
        return create(data);
    }

    public static <T> GeneralResult<T> empty(){
        GeneralResult<T> result = create();
        result.setSuccess(true);
        return result;
    }

    public static <T> GeneralResult<T> error(String code, String message) {
        return create(code,message);
    }

    public static <T,S> GeneralResult<T> error(GeneralResult<S> errorBizResult){
        return error(errorBizResult.getCode(),errorBizResult.getMessage());
    }

    public static <T> GeneralResult<T> paramsError(String msg){
        return GeneralResult.error(ErrorCodeEnum.PARAM_ERROR.getErrorCode(),
                ErrorCodeEnum.PARAM_ERROR.getErrorMeg() + "：" + msg
        );
    }

    public static <T> GeneralResult<T> systemError(String msg){
        return GeneralResult.error(
                ErrorCodeEnum.EXCEPTION.getErrorCode(),
                ErrorCodeEnum.EXCEPTION.getErrorMeg() + "：" + msg
        );
    }

    public GeneralResult() {
    }

    public static <T> GeneralResult<T> create() {
        return new GeneralResult<T>();
    }

    /**
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> GeneralResult<T> create(T data) {
        GeneralResult<T> bizResult = create();
        bizResult.setSuccess(true);
        bizResult.setData(data);
        return bizResult;
    }

    /**
     * 接口调用成功时也需要code和message的场景
     * @param data
     * @param code
     * @param message
     * @return
     */
    public static <T> GeneralResult<T> create(T data, String code, String message) {
        GeneralResult<T> result = GeneralResult.create();
        result.setSuccess(true);
        result.setData(data);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 任意场景
     * @param isSuccess
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static <T> GeneralResult<T> create(T data, boolean isSuccess, String code, String message) {
        GeneralResult<T> result = GeneralResult.create();
        result.setSuccess(isSuccess);
        result.setData(data);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 任意场景
     * @param isSuccess
     * @param data
     * @return
     */
    public static <T> GeneralResult<T> create(T data, boolean isSuccess) {
        GeneralResult<T> result = GeneralResult.create();
        result.setSuccess(isSuccess);
        result.setData(data);
        return result;
    }

    /**
     * @param data
     * @param extraInfo
     * @param <T>
     * @return
     */
    public static <T> GeneralResult<T> create(T data, Map<String,Object> extraInfo){
        GeneralResult<T> bizResult = create();
        bizResult.setSuccess(true);
        bizResult.setData(data);
        bizResult.setExtraInfo(extraInfo);
        return bizResult;
    }

    /**
     * @param data
     * @param extraInfo
     * @param isSuccess
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> GeneralResult<T> create(T data, Map<String,Object> extraInfo, boolean isSuccess, String code, String message){
        GeneralResult<T> bizResult = create();
        bizResult.setSuccess(true);
        bizResult.setData(data);
        bizResult.setExtraInfo(extraInfo);
        bizResult.setSuccess(isSuccess);
        bizResult.setCode(code);
        bizResult.setMessage(message);
        return bizResult;
    }

    /**
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> GeneralResult<T> create(String code, String message) {
        GeneralResult<T> bizResult = create();
        bizResult.setSuccess(false);
        bizResult.setCode(code);
        bizResult.setMessage(message);
        return bizResult;
    }

    /**
     *
     * @param errorCodeEnum
     * @param <T>
     * @return
     */
    public static <T> GeneralResult<T> create(ErrorCodeEnum errorCodeEnum) {
        GeneralResult<T> bizResult = create();
        bizResult.setSuccess(false);
        bizResult.setCode(errorCodeEnum.getErrorCode());
        bizResult.setMessage(errorCodeEnum.getErrorMeg());
        return bizResult;
    }

    public Map<String, Object> getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(Map<String, Object> extraInfo) {
        this.extraInfo = extraInfo;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
