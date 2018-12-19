package com.ly.common.base;

import java.io.Serializable;

/**
 * 接口响应体统一返回格式
 *
 * @param <T>
 */
public class ResponseResult<T> implements Serializable {

    private int code;
    private String msg;
    private T data;

    public static ResponseResult result(MsgCode msgCode) {
        ResponseResult responseMessage = getInstance();
        responseMessage.code = msgCode.getCode();
        responseMessage.msg = msgCode.getMsg();
        return responseMessage;
    }

    public static <T> ResponseResult<T> success(T data) {
        ResponseResult responseMessage = result(MsgCode.SUCCESS);
        responseMessage.setData(data);
        return responseMessage;
    }
    public static <T> ResponseResult<T> success() {
        ResponseResult responseMessage = result(MsgCode.SUCCESS);
        return responseMessage;
    }
    public static ResponseResult error(String message) {
        ResponseResult responseMessage = getInstance();
        responseMessage.setCode(MsgCode.ERROR.getCode());
        responseMessage.setMsg(message);
        return responseMessage;
    }

    public ResponseResult() {
    }

    public static ResponseResult getInstance() {
        return new ResponseResult();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
