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
    HTTP_REQUEST_GET_PARAM_FAIL(500002,false,"请求获取认证参数失败"),
    GET_PARAM_CODE_FAIL(500003,false,"获取参数Code失败"),
    LOGIN_HTTP_FAIL(50004,false,"登陆请求失败"),
    CODE_IS_VALIDATE_FAIL(50005,false,"验证码错误"),
    CODE_DOES_NOT_EXIST(50006,false,"验证码不存在或已过期!"),
    LOGIN_TYPE_NOT_MATCH(50007,false,"登录方式不匹配!"),
    USER_DETAILS_IS_NULL(50008,false,"获取用户数据失败!"),
    CREATE_CAPTCHA_FAIL(50009,false,"图片验证码生成失败!"),
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
