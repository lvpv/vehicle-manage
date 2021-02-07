package com.lv.vehicle.security.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.security.common
 * FileName: CaptchaPropertirs
 * Author: lv
 * Date: 2021/2/7 16:37
 * Description: 自定义图片验证码配置属性类
 */
@Data
@ConfigurationProperties(prefix = "security.captcha")
public class CaptchaProperties {

    // 图片宽度
    private int width = 130;

    //图片高度
    private int height = 48;

    // 验证码位数
    private int len = 4;

    // 验证码类型
    private CaptchaType captchaType = CaptchaType.PNG;

    private float fontSize = 32.0F;

    // 验证码字符类型
    private CaptchaChar charType = CaptchaChar.TYPE_DEFAULT;

    private CaptchaFont captchaFont = CaptchaFont.FONT_1;

    private boolean isBase64 = true;




}
