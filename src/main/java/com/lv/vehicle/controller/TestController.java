package com.lv.vehicle.controller;

import com.lv.vehicle.common.Result;
import com.lv.vehicle.security.common.CaptchaProperties;
import com.lv.vehicle.security.common.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CaptchaProperties captchaProperties;

    @Autowired
    private JwtProperties jwtProperties;

    @GetMapping("/prop")
    public Result<Map<String,Object>> getProperties(){
        Map<String, Object> map = new HashMap<>();
        map.put("captcha",captchaProperties);
        map.put("jwt",jwtProperties);
        return Result.ok(map);
    }
}
