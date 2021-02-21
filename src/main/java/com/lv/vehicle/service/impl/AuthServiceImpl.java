package com.lv.vehicle.service.impl;

import com.lv.vehicle.constant.VehicleConstant;
import com.lv.vehicle.domain.User;
import com.lv.vehicle.exception.AuthException;
import com.lv.vehicle.exception.ExceptionCode;
import com.lv.vehicle.redis.RedisUtil;
import com.lv.vehicle.security.dingtalk.CodeAuthenticationToken;
import com.lv.vehicle.security.service.TokenService;
import com.lv.vehicle.security.vo.AuthData;
import com.lv.vehicle.security.vo.AuthUser;
import com.lv.vehicle.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Override
    public String login(AuthData authData) {
        if (authData == null) {
            throw new AuthException(ExceptionCode.LOGIN_HTTP_FAIL);
        }
        String username = authData.getUsername();
        String password = authData.getPassword();
        String codeId = authData.getCodeId();
        String code = authData.getCode();
        if (StringUtils.isBlank(codeId)) {
            throw new AuthException(ExceptionCode.CODE_ID_NOT_EXIST);
        }
        if (StringUtils.isBlank(code)) {
            throw new AuthException(ExceptionCode.CODE_IS_NOT_EXIST);
        }
        if (StringUtils.isBlank(username)) {
            throw new AuthException(ExceptionCode.USERNAME_IS_NOT_EXIST);
        }
        if (StringUtils.isBlank(password)) {
            throw new AuthException(ExceptionCode.PASSWORD_IS_NOT_EXIST);
        }
        // 验证图片验证码
        validateCode(codeId, code);
        // 登陆
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username.trim(), password);
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(token);
        }catch (AuthenticationException authenticationException){
            if (authenticationException instanceof LockedException) { // 账户被锁定
                throw new AuthException(ExceptionCode.ACCOUNT_IS_LOCKED);
            } else if (authenticationException instanceof CredentialsExpiredException) {  // 密码已经过期
                throw new AuthException(ExceptionCode.PASSWORD_IS_CREDENTIALS_EXPIRED);
            } else if (authenticationException instanceof AccountExpiredException) { // 账户过期
                throw new AuthException(ExceptionCode.ACCOUNT_IS_EXPIRED);
            } else if (authenticationException instanceof DisabledException) { // 账户被禁用
                throw new AuthException(ExceptionCode.ACCOUNT_IS_DISABLED);
            } else if (authenticationException instanceof BadCredentialsException) {  // 用户名或密码错误
                throw new AuthException(ExceptionCode.BAD_CREDENTIALS);
            }
        }
        if (authentication == null){
            throw new AuthException(ExceptionCode.AUTH_LOGIN_FAIL);
        }
        User user = (User) authentication.getPrincipal();
        AuthUser authUser = new AuthUser();
        BeanUtils.copyProperties(user,authUser);
        return tokenService.generateToken(authUser);
    }

    @Override
    public String loginByCode(AuthData authData) {
        if (authData == null) {
            throw new AuthException(ExceptionCode.LOGIN_HTTP_FAIL);
        }
        String code = authData.getCode();
        if (StringUtils.isBlank(code)){
            throw new AuthException(ExceptionCode.DING_TALK_AUTH_CODE_IS_NULL);
        }
        CodeAuthenticationToken token = new CodeAuthenticationToken(code);
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(token);
        }catch (AuthenticationException authenticationException){
            if (authenticationException instanceof LockedException) { // 账户被锁定
                throw new AuthException(ExceptionCode.ACCOUNT_IS_LOCKED);
            } else if (authenticationException instanceof CredentialsExpiredException) {  // 密码已经过期
                throw new AuthException(ExceptionCode.PASSWORD_IS_CREDENTIALS_EXPIRED);
            } else if (authenticationException instanceof AccountExpiredException) { // 账户过期
                throw new AuthException(ExceptionCode.ACCOUNT_IS_EXPIRED);
            } else if (authenticationException instanceof DisabledException) { // 账户被禁用
                throw new AuthException(ExceptionCode.ACCOUNT_IS_DISABLED);
            } else if (authenticationException instanceof BadCredentialsException) {  // 用户名或密码错误
                throw new AuthException(ExceptionCode.BAD_CREDENTIALS);
            }
        }
        if (authentication == null){
            throw new AuthException(ExceptionCode.AUTH_LOGIN_FAIL);
        }
        User user = (User) authentication.getPrincipal();
        AuthUser authUser = new AuthUser();
        BeanUtils.copyProperties(user,authUser);
        return tokenService.generateToken(authUser);
    }

    private void validateCode(String codeId, String code) {
        String key = VehicleConstant.VALID_CODE_REDIS_KEY + codeId;
        // 获取验证码,验证验证码
        String cacheCode = (String) redisUtil.get(key);
        // 删除缓存验证码
        redisUtil.del(key);
        if (StringUtils.isBlank(cacheCode)) {
            throw new AuthException(ExceptionCode.CODE_DOES_NOT_EXIST);
        }
        if (!StringUtils.equals(code.toLowerCase(), cacheCode)) {
            throw new AuthException(ExceptionCode.CODE_IS_VALIDATE_FAIL);
        }
    }
}
