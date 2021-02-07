package com.lv.vehicle.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lv.vehicle.common.CommonCode;
import com.lv.vehicle.common.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
 * FileName: MyAccessDeniedHandler
 * Author: lv
 * Date: 2021/2/6 22:24
 * Description: 自定义权限不足处理器
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    /**
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param accessDeniedException AccessDeniedException 权限不足异常
     */

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Result<Object> failed = Result.failed(CommonCode.INSUFFICIENT_AUTHORITY);
        out.write(new ObjectMapper().writeValueAsString(failed));
        out.flush();
        out.close();
    }
}
