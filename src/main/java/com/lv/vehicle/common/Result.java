package com.lv.vehicle.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.common
 * FileName: Result
 * Author: lv
 * Date: 2021/2/4 21:11
 * Description: 描述信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class Result<T> implements Serializable {
    private int code;

    private boolean success;

    private String message;

    private T data;

    public static <T> Result<T> ok() {
        return restResult(CommonCode.HANDLER_SUCCESS.code(), CommonCode.HANDLER_SUCCESS.success(), CommonCode.HANDLER_SUCCESS.message(),null);
    }

    public static <T> Result<T> ok(T data) {
        return restResult(CommonCode.HANDLER_SUCCESS.code(), CommonCode.HANDLER_SUCCESS.success(), CommonCode.HANDLER_SUCCESS.message(),data);
    }

    public static <T> Result<T> ok(ResultCode resultCode) {
        return restResult(resultCode.code(), resultCode.success(), resultCode.message(),null);
    }

    public static <T> Result<T> ok(String message) {
        return restResult(CommonCode.HANDLER_SUCCESS.code(), CommonCode.HANDLER_SUCCESS.success(), message,null);
    }

    public static <T> Result<T> ok(String message,T data) {
        return restResult(CommonCode.HANDLER_SUCCESS.code(), CommonCode.HANDLER_SUCCESS.success(), message,data);
    }

    public static <T> Result<T> failed() {
        return restResult(CommonCode.SERVER_ERROR.code(), CommonCode.SERVER_ERROR.success(), CommonCode.SERVER_ERROR.message(),null);
    }
    public static <T> Result<T> failed(T data) {
        return restResult(CommonCode.SERVER_ERROR.code(), CommonCode.SERVER_ERROR.success(), CommonCode.SERVER_ERROR.message(),data);
    }

    public static <T> Result<T> failed(ResultCode resultCode) {
        return restResult(resultCode.code(), resultCode.success(), resultCode.message(),null);
    }

    public static <T> Result<T> failed(String message) {
        return restResult(CommonCode.SERVER_ERROR.code(), CommonCode.SERVER_ERROR.success(), message,null);
    }
    public static <T> Result<T> failed(String message,T data) {
        return restResult(CommonCode.SERVER_ERROR.code(), CommonCode.SERVER_ERROR.success(), message,data);
    }

    private static <T> Result<T> restResult(int code, boolean success,String message,T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setSuccess(success);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

}
