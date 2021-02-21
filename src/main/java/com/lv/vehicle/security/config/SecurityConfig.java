package com.lv.vehicle.security.config;

import com.lv.vehicle.constant.VehicleConstant;
import com.lv.vehicle.security.common.CaptchaProperties;
import com.lv.vehicle.security.common.JwtProperties;
import com.lv.vehicle.security.dingtalk.CodeAuthenticationProvider;
import com.lv.vehicle.security.dingtalk.CodeSecurityConfigurerAdapter;
import com.lv.vehicle.security.filter.TokenFilter;
import com.lv.vehicle.security.handler.MyAccessDeniedHandler;
import com.lv.vehicle.security.handler.MyAuthenticationEntryPoint;
import com.lv.vehicle.security.handler.MyLogoutSuccessHandler;
import com.lv.vehicle.security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
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
@EnableConfigurationProperties({CaptchaProperties.class, JwtProperties.class})
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private JwtProperties jwtProperties;
//
//    @Autowired
//    private RedisUtil redisUtil;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private UserDetailsService myCodeUserDetailsService;

//    @Autowired
//    private MyAuthenticationSuccessHandler authenticationSuccessHandler;

   // @Autowired
   // private MyAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private MyLogoutSuccessHandler logoutSuccessHandler;

    // 自定义权限不足异常
    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    // 自定义未登陆异常
    @Autowired
    private MyAuthenticationEntryPoint authenticationEntryPoint;

//    @Autowired
//    private CodeSecurityConfigurerAdapter codeSecurityConfigurerAdapter;

    @Autowired
    private TokenFilter tokenFilter;


    //读取密钥的配置


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    public CodeSecurityConfigurerAdapter codeSecurityConfigurerAdapter() {
        CodeAuthenticationProvider codeAuthenticationProvider = new CodeAuthenticationProvider(myCodeUserDetailsService);
        return new CodeSecurityConfigurerAdapter(codeAuthenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**","/js/**","/images/**")
                .antMatchers("/swagger-ui.html")
                .antMatchers("/webjars/**")
                .antMatchers("/v2/**")
                .antMatchers("/swagger-resources/**");
    }

   /* @Bean
    public VerificationCodeFilter verificationCodeFilter() throws Exception {
        VerificationCodeFilter verificationCodeFilter= new VerificationCodeFilter();
        verificationCodeFilter.setPostOnly(true);
        verificationCodeFilter.setRedisUtil(redisUtil);
        verificationCodeFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        verificationCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        verificationCodeFilter.setAuthenticationManager(authenticationManagerBean());
        verificationCodeFilter.setFilterProcessesUrl(VehicleConstant.DEFAULT_LOGIN_PATH);
        return verificationCodeFilter;
    }*/




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers(VehicleConstant.DEFAULT_LOGIN_PATH).permitAll()
                .antMatchers(HttpMethod.GET,VehicleConstant.AUTH_CODE_PATH).permitAll() // 获取验证码
                .antMatchers(HttpMethod.POST,VehicleConstant.AUTH_LOGIN_PATH).permitAll()
                .antMatchers(HttpMethod.OPTIONS, VehicleConstant.AUTH_OPTION_PATH).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
               // .successHandler(authenticationSuccessHandler)
               // .failureHandler(authenticationFailureHandler)
                .and() //session会话管理：不启用
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
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
//        http.addFilterBefore(tokenFilter(),UsernamePasswordAuthenticationFilter.class);
//        http.addFilterAt(verificationCodeFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.apply(codeSecurityConfigurerAdapter());


    }
}
