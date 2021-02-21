package com.lv.vehicle.security.service;

import com.lv.vehicle.exception.ExceptionCode;
import com.lv.vehicle.mapper.UserMapper;
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
 * Date: 2021/2/6 19:52
 * Description: 描述信息
 */
@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    public MyUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userMapper.loadUserByUsername(username);
        if (userDetails == null){
            throw new UsernameNotFoundException(ExceptionCode.USERNAME_PASSWORD_IS_ERROR.message());
        }
        return userDetails;
    }
}
