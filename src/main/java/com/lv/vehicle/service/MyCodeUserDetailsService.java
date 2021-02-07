package com.lv.vehicle.service;

import com.lv.vehicle.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.service
 * FileName: MyUserDetailsService
 * Author: lv
 * Date: 2021/2/6 1:00
 * Description: 描述信息
 */
@Service("myCodeUserDetailsService")
public class MyCodeUserDetailsService implements UserDetailsService {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    /**
     *
     * @param code 免登授权码
     * @return 用户信息
     * @throws UsernameNotFoundException 用户未找到异常
     */
    @Override
    public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
        User admin = (User) myUserDetailsService.loadUserByUsername("admin");
        admin.setDingId("123456");
        // 根据免登授权码到钉钉服务器获取用户userId
        // 根据userId去数据库获取用户信息数据
        // 如果数据库存在，将数据库用户信息封装为UserDetails信息并返回
        // 如果数据库不存在用信息，则使用userId去钉钉服务器获取用户信息
        // 将用户信息转化为数据库需要的用户信息
        // 将数据库用户信息封装为UserDetails信息并返回
        return admin;
    }
}
