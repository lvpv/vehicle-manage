package com.lv.vehicle.controller;

import com.lv.vehicle.common.Result;
import com.lv.vehicle.constant.VehicleConstant;
import com.lv.vehicle.exception.ExceptionCode;
import com.lv.vehicle.exception.VehicleException;
import com.lv.vehicle.redis.RedisUtil;
import com.lv.vehicle.security.common.CaptchaProperties;
import com.lv.vehicle.security.vo.AuthData;
import com.lv.vehicle.security.vo.ImageResult;
import com.lv.vehicle.service.AuthService;
import com.lv.vehicle.utils.CaptchaUtil;
import com.wf.captcha.base.Captcha;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.util.UUID;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.controller
 * FileName: AuthController
 * Author: lv
 * Date: 2021/2/6 20:18
 * Description: 描述信息
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final CaptchaProperties captchaProperties;

    private final RedisUtil redisUtil;

    private final AuthService authService;

    public AuthController(CaptchaProperties captchaProperties, RedisUtil redisUtil, AuthService authService) {
        this.captchaProperties = captchaProperties;
        this.redisUtil = redisUtil;
        this.authService = authService;
    }


    @GetMapping("/code")
    public Result<ImageResult> hello(){
        Captcha captcha;
        try {
            captcha = CaptchaUtil.createCaptcha(captchaProperties);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            throw new VehicleException(ExceptionCode.CREATE_CAPTCHA_FAIL);
        }
        String verCode = captcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        redisUtil.set(VehicleConstant.VALID_CODE_REDIS_KEY+key,verCode,VehicleConstant.VALID_CODE_REDIS_EXPIRATION);
        ImageResult imageResult = new ImageResult(key,captcha.toBase64(),verCode);
        return Result.ok(imageResult);
    }

    @PostMapping("/login")
    public Result<String> loginByUsername(@RequestBody AuthData authData){
        String token = authService.login(authData);
        return Result.ok(token);
    }

    @PostMapping("/login/code")
    public Result<String> loginByCode(@RequestBody AuthData authData){
        String token = authService.loginByCode(authData);
        return Result.ok(token);
    }
}
