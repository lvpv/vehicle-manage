package com.lv.vehicle.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lv.vehicle.common.CommonCode;
import com.lv.vehicle.common.Result;
import com.lv.vehicle.exception.AuthException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
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
 * FileName: MyAuthenticationFailureHandler
 * Author: lv
 * Date: 2021/2/6 22:24
 * Description: 自定义认证失败处理器
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    /**
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param authenticationException 保存了登录失败的原因
     */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Result<Object> failed = Result.failed(CommonCode.LOGIN_SYSTEM_FAIL);
        if (authenticationException instanceof LockedException) {
            failed.setMessage("账户被锁定，请联系管理员!");
        } else if (authenticationException instanceof CredentialsExpiredException) {
            failed.setMessage("密码过期，请联系管理员!");
        } else if (authenticationException instanceof AccountExpiredException) {
            failed.setMessage("账户过期，请联系管理员!");
        } else if (authenticationException instanceof DisabledException) {
            failed.setMessage("账户被禁用，请联系管理员!");
        } else if (authenticationException instanceof BadCredentialsException) {
            failed.setMessage("用户名或者密码输入错误，请重新输入!");
        }else if (authenticationException instanceof AuthException){
            failed.setMessage(authenticationException.getMessage());
        }

        out.write(new ObjectMapper().writeValueAsString(failed));
        out.flush();
        out.close();
    }
}
