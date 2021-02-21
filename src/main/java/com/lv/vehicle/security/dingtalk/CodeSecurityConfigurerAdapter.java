package com.lv.vehicle.security.dingtalk;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.security
 * FileName: CodeSecurityConfigurerAdapter
 * Author: lv
 * Date: 2021/2/6 1:03
 * Description: 描述信息
 */

public class CodeSecurityConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//
//    private final AuthenticationFailureHandler authenticationFailureHandler;
//
//    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    private  CodeAuthenticationProvider codeAuthenticationProvider;

   // private AuthenticationManager authenticationManager;

    public CodeSecurityConfigurerAdapter(CodeAuthenticationProvider codeAuthenticationProvider) {
        this.codeAuthenticationProvider = codeAuthenticationProvider;
    }


//    public AuthenticationManager getAuthenticationManager() {
//        return authenticationManager;
//    }
//
//    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
//        this.authenticationManager = authenticationManager;
//    }


    //    public CodeSecurityConfigurerAdapter(AuthenticationFailureHandler authenticationFailureHandler,
//                                         AuthenticationSuccessHandler authenticationSuccessHandler,
//                                         @Qualifier("myCodeUserDetailsService") UserDetailsService userDetailsService,
//                                         AuthenticationManager authenticationManager) {
//        this.authenticationFailureHandler = authenticationFailureHandler;
//        this.authenticationSuccessHandler = authenticationSuccessHandler;
//        this.userDetailsService = userDetailsService;
//        this.authenticationManager = authenticationManager;
//    }

    @Override
    public void configure(HttpSecurity http) {
//        CodeAuthenticationFilter codeAuthenticationFilter = new CodeAuthenticationFilter();
//        codeAuthenticationFilter.setPostOnly(true);
//        codeAuthenticationFilter.setAuthenticationManager(authenticationManager);
//        codeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
//        codeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
//        codeAuthenticationFilter.setFilterProcessesUrl(VehicleConstant.DEFAULT_LOGIN_PATH);


//        codeAuthenticationProvider.setUserDetailsService(userDetailsService);
        http.authenticationProvider(codeAuthenticationProvider);
//        http.addFilterAfter(codeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
