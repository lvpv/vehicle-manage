package com.lv.vehicle.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lv.vehicle.common.CommonCode;
import com.lv.vehicle.common.Result;
import com.lv.vehicle.common.ResultCode;
import com.lv.vehicle.constant.VehicleConstant;
import com.lv.vehicle.exception.AuthException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
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
 * FileName: MyAuthenticationEntryPoint
 * Author: lv
 * Date: 2021/2/6 22:24
 * Description: 自定义未登录访问受保护资源的处理器
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * @param request                 HttpServletRequest
     * @param response                HttpServletResponse
     * @param authenticationException SpringSecurity定义的未认证请求保护资源异常类
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        response.setContentType(VehicleConstant.CONTENT_TYPE_UTF);
        PrintWriter out = response.getWriter();
        Result<Object> failed;
        if (authenticationException instanceof AuthException) {
            ResultCode resultCode = ((AuthException) authenticationException).getResultCode();
            failed = Result.failed(resultCode);
        } else {
            failed = Result.failed(CommonCode.NOT_LOGIN_SYSTEM);
        }
        out.write(new ObjectMapper().writeValueAsString(failed));
        out.flush();
        out.close();
    }
}
