package com.lv.vehicle.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.exception
 * FileName: AuthException
 * Author: lv
 * Date: 2021/2/6 21:17
 * Description: 认证过程中的自定义异常
 */
public class AuthException extends AuthenticationException {

    public AuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthException(String message){
        super(message);
    }
}
