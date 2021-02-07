package com.lv.vehicle.security.dingtalk;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lv.vehicle.constant.VehicleConstant;
import com.lv.vehicle.exception.AuthException;
import com.lv.vehicle.exception.ExceptionCode;
import com.lv.vehicle.security.common.LoginType;
import com.lv.vehicle.security.common.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.security
 * FileName: CodeAuthenticationFilter
 * Author: lv
 * Date: 2021/2/6 0:40
 * Description: 描述信息
 */
public class CodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String AUTH_CODE_KEY = VehicleConstant.AUTH_CODE_PARAM; // code
    private String codeParameter = AUTH_CODE_KEY;
    private boolean postOnly = true;

    // 拦截登录请求进行认证处理
    public CodeAuthenticationFilter(){ // path: /auth/login
        super(new AntPathRequestMatcher(VehicleConstant.AUTH_LOGIN_PATH, VehicleConstant.POST_METHOD));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (this.postOnly && !request.getMethod().equals(VehicleConstant.POST_METHOD)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String code = this.obtainCode(request);
            if (code == null) {
                code = "";
            }

            code = code.trim();
            // 生成token
            CodeAuthenticationToken authRequest = new CodeAuthenticationToken(code);

            this.setDetails(request, authRequest);
            // 调用 AuthenticationManager
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }

    protected void setDetails(HttpServletRequest request,
                              CodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    /**
     * 获取免登授权码 code
     * @param request request
     * @return code
     */
    @Nullable
    protected String obtainCode(HttpServletRequest request) {
        String contentType = request.getContentType();
        String changeContentType = StringUtils.isBlank(contentType)? null:contentType.trim().toLowerCase();
        String code = null;
        if (StringUtils.isNotBlank(changeContentType)
                &&(VehicleConstant.CONTENT_TYPE_UTF.equals(changeContentType)
                || VehicleConstant.CONTENT_TYPE.equals(changeContentType))){
            try {
                UserDTO userDTO = new ObjectMapper().readValue(request.getInputStream(), UserDTO.class);
                if (userDTO == null || userDTO.getType() == null || StringUtils.isBlank(userDTO.getCode()) ){
                    throw new AuthException(ExceptionCode.GET_PARAM_CODE_FAIL.message());
                }
                if (LoginType.DING_TALK.getCode() != userDTO.getType()){
                    throw new AuthException(ExceptionCode.LOGIN_TYPE_NOT_MATCH.message());
                }
                code = userDTO.getCode();
            } catch (IOException e) {
                e.printStackTrace();
                throw new AuthException(ExceptionCode.HTTP_REQUEST_GET_PARAM_FAIL.message());
            }
        }
        return code;
    }

    public void setCodeParameter(String codeParameter) {
        Assert.hasText(codeParameter, "Username parameter must not be empty or null");
        this.codeParameter = codeParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getCodeParameter() {
        return this.codeParameter;
    }
}
