package com.lv.vehicle.security.common;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.security.common
 * FileName: CaptchaType
 * Author: lv
 * Date: 2021/2/7 16:50
 * Description: 图片验证码字符类型
 *  参考地址: https://gitee.com/whvse/EasyCaptcha#52%E9%AA%8C%E8%AF%81%E7%A0%81%E5%AD%97%E7%AC%A6%E7%B1%BB%E5%9E%8B
 */
public enum CaptchaChar {

    TYPE_DEFAULT(1),
    TYPE_ONLY_NUMBER(2),
    TYPE_ONLY_CHAR(3),
    TYPE_ONLY_UPPER(4),
    TYPE_ONLY_LOWER(5),
    TYPE_NUM_AND_UPPER(6);
    ;

    private int code;


    CaptchaChar(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
