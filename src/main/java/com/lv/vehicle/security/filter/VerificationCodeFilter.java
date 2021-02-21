package com.lv.vehicle.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lv.vehicle.constant.VehicleConstant;
import com.lv.vehicle.exception.AuthException;
import com.lv.vehicle.exception.ExceptionCode;
import com.lv.vehicle.redis.RedisUtil;
import com.lv.vehicle.security.vo.AuthData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.security.filter
 * FileName: VerificationCodeFilter
 * Author: lv
 * Date: 2021/2/7 17:49
 * Description: 在用户名密码认证前加入验证码校验  未使用
 */
public class VerificationCodeFilter extends UsernamePasswordAuthenticationFilter {

    private boolean postOnly = true;
    private RedisUtil redisUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        if (this.postOnly && !VehicleConstant.POST_METHOD.equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String contentType = request.getContentType();
        if (StringUtils.isNotBlank(contentType)
                && (VehicleConstant.CONTENT_TYPE_UTF.equalsIgnoreCase(contentType.trim())
                || VehicleConstant.CONTENT_TYPE.equalsIgnoreCase(contentType.trim()))) {
            AuthData authData;
            try {
                authData = new ObjectMapper().readValue(request.getInputStream(), AuthData.class);
            } catch (IOException e) {
                e.printStackTrace();
                throw new AuthException(ExceptionCode.LOGIN_HTTP_FAIL.message());
            }
            if (authData == null){
                throw new AuthException(ExceptionCode.LOGIN_HTTP_FAIL.message());
            }
            String uuid = authData.getCodeId();
            String code = authData.getCode();
            validateCode(uuid,code);

            String username = authData.getUsername();
            String password = authData.getPassword();
            if (username == null) {
                username = "";
            }
            if (password == null) {
                password = "";
            }
            username = username.trim();
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    username, password);
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            // 校验验证码
            String uuid = request.getParameter(VehicleConstant.AUTH_UUID_PARAM);
            String code = request.getParameter(VehicleConstant.AUTH_CODE_PARAM);
            validateCode(uuid, code);
            return super.attemptAuthentication(request, response);
        }
    }

    private void validateCode(String uuid,String code){
        if (StringUtils.isBlank(uuid) || StringUtils.isBlank(code)){
            throw new AuthException(ExceptionCode.GET_PARAM_CODE_FAIL.message());
        }

        String key = VehicleConstant.VALID_CODE_REDIS_KEY + uuid;
        // 获取验证码,验证验证码
        String cacheCode = (String) redisUtil.get(key);
        // 删除缓存验证码
        redisUtil.del(key);
        if (StringUtils.isBlank(cacheCode)){
            throw new AuthException(ExceptionCode.CODE_DOES_NOT_EXIST.message());
        }
        if (!StringUtils.equals(code.toLowerCase(),cacheCode)){
            throw new AuthException(ExceptionCode.CODE_IS_VALIDATE_FAIL.message());
        }
    }

    public boolean isPostOnly() {
        return postOnly;
    }

    @Override
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public RedisUtil getRedisUtil() {
        return redisUtil;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
}
