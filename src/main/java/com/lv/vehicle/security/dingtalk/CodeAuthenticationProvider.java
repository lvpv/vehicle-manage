package com.lv.vehicle.security.dingtalk;

import com.lv.vehicle.exception.AuthException;
import com.lv.vehicle.exception.ExceptionCode;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.security
 * FileName: MyAuthenticationProvider
 * Author: lv
 * Date: 2021/2/6 0:31
 * Description: 描述信息
 */
public class CodeAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    public CodeAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     *  进行身份认证的逻辑
     * @param authentication 认证信息
     * @return 认证信息
     * @throws AuthenticationException 认证异常
     */


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CodeAuthenticationToken authenticationToken = (CodeAuthenticationToken)authentication;
        // 根据免登授权码获取用户信息
        UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        if (user == null) {
            throw new AuthException(ExceptionCode.USER_DETAILS_IS_NULL.message());
        }
        CodeAuthenticationToken authenticationResult = new CodeAuthenticationToken(user, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    /**
     * 根据该方法判断 AuthenticationManager选择哪个 Provider进行认证处理
     * @param authentication 认证信息
     * @return 是否支持
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return CodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

//    public UserDetailsService getUserDetailsService() {
//        return userDetailsService;
//    }
//
//    public void setUserDetailsService(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
}
