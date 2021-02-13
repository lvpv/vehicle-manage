package com.lv.vehicle.security.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.security.common
 * FileName: CaptchaProperties
 * Author: lv
 * Date: 2021/2/7 16:37
 * Description: 自定义图片验证码配置属性类
 */

@ConfigurationProperties(prefix = "vehicle.captcha")
public class CaptchaProperties {

    // 验证码有效期
    private Integer expiration = 2;

    // 图片宽度
    private Integer width = 130;

    //图片高度
    private Integer height = 48;

    // 验证码位数
    private Integer len = 4;

    // 验证码类型
    private CaptchaType captchaType = CaptchaType.PNG;

    private Float fontSize = 32.0F;

    // 验证码字符类型
    private CaptchaChar charType = CaptchaChar.TYPE_DEFAULT;

    private CaptchaFont captchaFont = CaptchaFont.FONT_1;

    private Boolean base64 = true;


    public Integer getExpiration() {
        return expiration;
    }

    public void setExpiration(Integer expiration) {
        if (expiration == null || expiration <= 0 || expiration > 5) {
            this.expiration = 2;
        } else {
            this.expiration = expiration;
        }
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        if (width == null || width <= 0 || width>200){
            this.width = 130;
        }else {
            this.width = width;
        }

    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        if (height == null || height <= 0 || height>100){
            this.height = 48;
        }else {
            this.height = height;
        }
    }

    public Integer getLen() {
        return len;
    }

    public void setLen(Integer len) {
        if (len == null || len<=0 || len >= 6){
            this.len = 6;
        }else {
            this.len = len;
        }

    }

    public CaptchaType getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(CaptchaType captchaType) {
        if (captchaType == null){
            this.captchaType = CaptchaType.PNG;
        }else {
            this.captchaType = captchaType;
        }

    }

    public Float getFontSize() {
        return fontSize;
    }

    public void setFontSize(Float fontSize) {
        if (fontSize == null || fontSize <= 0 || fontSize > 50.0F){
            this.fontSize = 32.0F;
        }else {
            this.fontSize = fontSize;
        }

    }

    public CaptchaChar getCharType() {
        return charType;
    }

    public void setCharType(CaptchaChar charType) {
        this.charType = charType;
    }

    public CaptchaFont getCaptchaFont() {
        return captchaFont;
    }

    public void setCaptchaFont(CaptchaFont captchaFont) {
        if (captchaFont == null){
            this.captchaFont = CaptchaFont.FONT_1;
        }else {
            this.captchaFont = captchaFont;
        }

    }

    public Boolean getBase64() {
        return base64;
    }

    public void setBase64(Boolean base64) {
        if (base64 == null){
            this.base64 = true;
        }else {
            this.base64 = base64;
        }

    }
}
