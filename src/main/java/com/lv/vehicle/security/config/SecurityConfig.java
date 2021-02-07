package com.lv.vehicle.security.config;

import com.lv.vehicle.constant.VehicleConstant;
import com.lv.vehicle.redis.RedisUtil;
import com.lv.vehicle.security.common.CaptchaProperties;
import com.lv.vehicle.security.filter.VerificationCodeFilter;
import com.lv.vehicle.security.handler.*;
import com.lv.vehicle.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.security.config
 * FileName: SecurityConfig
 * Author: lv
 * Date: 2021/2/7 16:01
 * Description: 描述信息
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(CaptchaProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private MyAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private MyLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private MyAuthenticationEntryPoint authenticationEntryPoint;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**","/images/**");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public VerificationCodeFilter verificationCodeFilter() throws Exception {
        VerificationCodeFilter verificationCodeFilter= new VerificationCodeFilter();
        verificationCodeFilter.setPostOnly(true);
        verificationCodeFilter.setRedisUtil(redisUtil);
        verificationCodeFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        verificationCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        verificationCodeFilter.setAuthenticationManager(authenticationManagerBean());
        verificationCodeFilter.setFilterProcessesUrl(VehicleConstant.DEFAULT_LOGIN_PATH);
        return verificationCodeFilter;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(VehicleConstant.DEFAULT_LOGIN_PATH).permitAll()
                .antMatchers(VehicleConstant.AUTH_CODE_PATH).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .permitAll()
                .and()
                // 防止iframe 造成跨域
                .headers()
                .frameOptions()
                .disable()
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .csrf().disable();
        http.addFilterAt(verificationCodeFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
