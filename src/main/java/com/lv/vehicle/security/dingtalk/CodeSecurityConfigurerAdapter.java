package com.lv.vehicle.security.dingtalk;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.security
 * FileName: CodeSecurityConfigurerAdapter
 * Author: lv
 * Date: 2021/2/6 1:03
 * Description: 描述信息
 */
@Component
public class CodeSecurityConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final AuthenticationFailureHandler authenticationFailureHandler;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    private final UserDetailsService userDetailsService;


    public CodeSecurityConfigurerAdapter(AuthenticationFailureHandler authenticationFailureHandler,
                                         AuthenticationSuccessHandler authenticationSuccessHandler,
                                         @Qualifier("myCodeUserDetailsService") UserDetailsService userDetailsService) {
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(HttpSecurity http) {
        CodeAuthenticationFilter codeAuthenticationFilter = new CodeAuthenticationFilter();
        codeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        codeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        codeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        CodeAuthenticationProvider codeAuthenticationProvider = new CodeAuthenticationProvider();
        codeAuthenticationProvider.setUserDetailsService(userDetailsService);
        http.authenticationProvider(codeAuthenticationProvider)
                .addFilterAfter(codeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
