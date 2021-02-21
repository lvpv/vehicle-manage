package com.lv.vehicle.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lv.vehicle.common.Result;
import com.lv.vehicle.constant.VehicleConstant;
import com.lv.vehicle.domain.User;
import com.lv.vehicle.exception.ExceptionCode;
import com.lv.vehicle.exception.VehicleException;
import com.lv.vehicle.security.common.JwtProperties;
import com.lv.vehicle.security.vo.AuthUser;
import com.lv.vehicle.utils.RsaUtil;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.PrivateKey;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.security
 * FileName: MyAuthenticationSuccessHandler
 * Author: lv
 * Date: 2021/2/6 22:24
 * Description: 自定义登录成功处理器（在自定义登陆处理器（/auth/login）后不会执行）
 */

public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final KeyProperties keyProperties;

    private final JwtProperties jwtProperties;

    public MyAuthenticationSuccessHandler(KeyProperties keyProperties, JwtProperties jwtProperties) {
        this.keyProperties = keyProperties;
        this.jwtProperties = jwtProperties;
    }

    /**
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param authentication 保存了我们刚刚登录成功的用户信息
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        PrivateKey privateKey = RsaUtil.getPrivateKey(keyProperties);
        if (privateKey == null){
            throw new VehicleException(ExceptionCode.PRIVATE_KEY_GET_FAIL);
        }
        AuthUser authUser = new AuthUser();
//        authUser.setUserId(user.getUserId());
//        authUser.setDingId(user.getDingId());
//        authUser.setUsername(user.getUsername());
//        authUser.setRealName(user.getRealName());
//        // 生成token
        // String token = JwtUtil.generateTokenExpireInSeconds(authUser, privateKey, jwtProperties.getTokenExpireSecond());
        // System.out.println("token = " + token);
        response.setContentType(VehicleConstant.CONTENT_TYPE_UTF);
        // 向客户端返回Token
        PrintWriter out = response.getWriter();
        Result<String> ok = Result.ok("OK");
        out.write(new ObjectMapper().writeValueAsString(ok));
        out.flush();
        out.close();
    }
}
