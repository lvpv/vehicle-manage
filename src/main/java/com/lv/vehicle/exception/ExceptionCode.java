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
    LOGIN_HTTP_FAIL(50004,false,"登陆请求参数获取失败"),
    CODE_IS_VALIDATE_FAIL(50005,false,"验证码错误"),
    CODE_DOES_NOT_EXIST(50006,false,"验证码不存在或已过期!"),
    CODE_ID_NOT_EXIST(50007,false,"验证码唯一标识为空!"),
    CODE_IS_NOT_EXIST(50007,false,"验证码不能为空!"),
    USERNAME_IS_NOT_EXIST(50007,false,"用户名不能为空!"),
    PASSWORD_IS_NOT_EXIST(50007,false,"密码不能为空!"),
    USER_DETAILS_IS_NULL(50008,false,"获取用户数据失败!"),
    CREATE_CAPTCHA_FAIL(50009,false,"图片验证码生成失败!"),
    GET_DING_TALK_USER_ID_FAIL(500010,false,"获取用户DingID失败!"),
    GET_DING_TALK_USER_INFO_FAIL(500011,false,"获取用户钉钉信息失败!"),
    USER_INFO_IS_NULL(500012,false,"用户信息为空!"),
    USER_INFO_SAVE_FAIL(500013,false,"用户信息保存失败!"),
    GENERATE_TOKEN_FAIL(500014,false,"Token信息生成失败!"),
    JWT_TOKEN_IS_NULL(500015,false,"获取Token信息失败!"),
    TOKEN_PARAS_FAIL(500016,false,"Token信息解析失败!"),
    TOKEN_IS_EXPIRED(500017,false,"Token信息已经过期!"),
    PRIVATE_KEY_GET_FAIL(500018,false,"私钥信息获取失败!"),
    USERNAME_PASSWORD_IS_ERROR(500019,false,"用户名或密码为空!"),
    USER_IS_NOT_EXIST(500020,false,"用户不存在!"),
    ACCOUNT_IS_LOCKED(500021,false,"账号已被锁定，请联系管理员!"),
    PASSWORD_IS_CREDENTIALS_EXPIRED(500022,false,"密码已过期，请联系管理员!"),
    ACCOUNT_IS_EXPIRED(500023,false,"账号已过期，请联系管理员!"),
    ACCOUNT_IS_DISABLED(500024,false,"账号已被禁用，请联系管理员!"),
    BAD_CREDENTIALS(500025,false,"用户名或者密码输入错误!"),
    AUTH_LOGIN_FAIL(500026,false,"登陆异常，请稍后再试!"),
    PUBLIC_KEY_GET_FAIL(500027,false,"公钥信息获取失败!"),
    DING_TALK_AUTH_CODE_IS_NULL(500027,false,"免登授权码为空!"),
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
