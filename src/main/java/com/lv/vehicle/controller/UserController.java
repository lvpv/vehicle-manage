package com.lv.vehicle.controller;

import com.lv.vehicle.common.Result;
import com.lv.vehicle.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.controller
 * FileName: UserController
 * Author: lv
 * Date: 2021/2/6 17:56
 * Description: 描述信息
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/info")
    public Result<User> testHello(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return Result.ok(user);
    }
}
