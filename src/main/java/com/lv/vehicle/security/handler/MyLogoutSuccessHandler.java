package com.lv.vehicle.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lv.vehicle.common.CommonCode;
import com.lv.vehicle.common.Result;
import com.lv.vehicle.constant.VehicleConstant;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
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
 * FileName: MyLogoutSuccessHandler
 * Author: lv
 * Date: 2021/2/6 22:24
 * Description: 自定义退出登录成功处理器(清除token和认证信息)
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    /**
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param authentication Authentication，认证管理器,可以获取登录用户信息
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(VehicleConstant.CONTENT_TYPE_UTF);
        PrintWriter out = response.getWriter();
        Result<Object> ok = Result.ok(CommonCode.LOGOUT_SYSTEM_SUCCESS);
        out.write(new ObjectMapper().writeValueAsString(ok));
        out.flush();
        out.close();
    }
}
