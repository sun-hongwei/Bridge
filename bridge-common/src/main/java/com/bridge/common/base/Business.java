package com.bridge.common.base;

/**
 * 处理业务返回 json公共类
 * @author bridge
 * @param <T>
 */
public class Business<T> {

    /**业务对象*/
    private T data;
    /**信息描述*/
    private String message;
    /**状态码 200 为默认成功*/
    private int code = 200;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Business{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
