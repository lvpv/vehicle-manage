package com.lv.vehicle.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lv.vehicle.common.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.security
 * FileName: MyAuthenticationSuccessHandler
 * Author: lv
 * Date: 2021/2/6 22:24
 * Description: 自定义登录成功处理器
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param authentication 保存了我们刚刚登录成功的用户信息
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        Object principal = authentication.getPrincipal();
        PrintWriter out = response.getWriter();
        Result<Object> ok = Result.ok(principal);
        out.write(new ObjectMapper().writeValueAsString(ok));
        out.flush();
        out.close();
    }
}
