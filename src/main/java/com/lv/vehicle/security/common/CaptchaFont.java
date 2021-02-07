package com.lv.vehicle.security.common;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.security.common
 * FileName: CaptchaType
 * Author: lv
 * Date: 2021/2/7 16:50
 * Description: 图片验证字体类型
 *  参考地址： https://gitee.com/whvse/EasyCaptcha#53%E5%AD%97%E4%BD%93%E8%AE%BE%E7%BD%AE
 */
public enum CaptchaFont {

    FONT_1 (0),
    FONT_2 (1),
    FONT_3 (2),
    FONT_4 (3),
    FONT_5 (4),
    FONT_6 (5),
    FONT_7 (6),
    FONT_8 (7),
    FONT_9 (8),
    FONT_10(9);


    private int code;


    CaptchaFont(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
