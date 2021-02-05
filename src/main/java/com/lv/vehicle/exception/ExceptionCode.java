package com.lv.vehicle.exception;

import com.lv.vehicle.common.ResultCode;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.exception
 * FileName: ExceptionCode
 * Author: lv
 * Date: 2021/2/4 21:19
 * Description: 描述信息
 */
public enum ExceptionCode implements ResultCode {
    GET_TOKEN_FAIL(500001,false,"获取Token失败"),
    ;

    /**
     * 操作代码
     */
    private boolean success;
    /**
     * //操作代码
     */
    private int code;
    /**
     * //操作代码
     */
    private String message;

    @Override
    public int code() {
        return code;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public String message() {
        return message;
    }

    ExceptionCode(){}

    ExceptionCode(int code, boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }
}
