package com.lv.vehicle.utils;

import com.lv.vehicle.security.common.CaptchaProperties;
import com.lv.vehicle.security.common.CaptchaType;
import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;

import java.awt.*;
import java.io.IOException;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.utils
 * FileName: CaptchaUtil
 * Author: lv
 * Date: 2021/2/7 17:05
 * Description: 图片验证码生成工具类
 */
public class CaptchaUtil {

    public static Captcha createCaptcha(CaptchaProperties properties) throws IOException, FontFormatException {
        Captcha captcha;
        if (properties.getCaptchaType() == CaptchaType.PNG){
            captcha = new SpecCaptcha();
        }else if (properties.getCaptchaType() == CaptchaType.GIF){
            captcha = new GifCaptcha();
        } else if (properties.getCaptchaType() == CaptchaType.CHINESE){
            captcha = new ChineseCaptcha();
        } else if (properties.getCaptchaType() == CaptchaType.CHINESE_GIF){
            captcha = new ChineseGifCaptcha();
        } else if (properties.getCaptchaType() == CaptchaType.ARITHMETIC){
            captcha = new ArithmeticCaptcha();
        }else {
            captcha = new SpecCaptcha();
        }

        captcha.setWidth(properties.getWidth());
        captcha.setHeight(properties.getHeight());
        captcha.setLen(properties.getLen());
        captcha.setCharType(properties.getCharType().getCode());
        captcha.setFont(properties.getCaptchaFont().getCode(),properties.getFontSize());
        if (!properties.getBase64()){
            captcha.toBase64("");
        }

        return captcha;
    }
}
