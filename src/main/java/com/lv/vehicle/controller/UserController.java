package com.lv.vehicle.controller;

import com.lv.vehicle.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

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
    public Result<Principal> testHello(Principal principal){
        return Result.ok(principal);
    }
}
