package com.lv.vehicle.common;

import java.io.Serializable;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.common
 * FileName: CommonCode
 * Author: lv
 * Date: 2021/2/4 21:12
 * Description: 描述信息
 */
public enum  CommonCode implements ResultCode, Serializable {
    // 20xxx
    HANDLER_SUCCESS(200,true,"操作成功"),
    LOGOUT_SYSTEM_SUCCESS(201,true,"退出登录成功"),

    // 40xxx
    NOT_LOGIN_SYSTEM(402,false,"您还未登录,请先登录系统"),
    INSUFFICIENT_AUTHORITY(403,false,"权限不足,请联系管理员"),

    // 50xxx
    SERVER_ERROR(500,false,"服务器错误"),
    LOGIN_SYSTEM_FAIL(501,false,"登录失败！"),
    ;

    private int code;

    private boolean success;

    private String message;

    CommonCode(int code, boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

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

    public void setCode(int code) {
        this.code = code;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
