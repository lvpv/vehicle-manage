package com.lv.vehicle.security.service;

import com.lv.vehicle.constant.VehicleConstant;
import com.lv.vehicle.domain.User;
import com.lv.vehicle.exception.ExceptionCode;
import com.lv.vehicle.exception.VehicleException;
import com.lv.vehicle.mapper.UserMapper;
import com.lv.vehicle.redis.RedisUtil;
import com.lv.vehicle.security.vo.DingTalkUser;
import com.lv.vehicle.utils.DingTalkUserUtil;
import com.lv.vehicle.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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

    private final RedisUtil redisUtil;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public MyCodeUserDetailsService(RedisUtil redisUtil, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.redisUtil = redisUtil;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     *
     * @param code 免登授权码
     * @return 用户信息
     * @throws UsernameNotFoundException 用户未找到异常
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
        String token = TokenUtil.getToken(redisUtil);
        // 根据免登授权码到钉钉服务器获取用户userId
        String dingId = DingTalkUserUtil.getUserDingId(code, token);
        // 根据userId去数据库获取用户信息数据
        User user = userMapper.findUserByDingId(dingId);
        // 如果数据库存在，将数据库用户信息封装为UserDetails信息并返回
        if (user != null){
            return user;
        }
        // 如果数据库不存在用信息，则使用userId去钉钉服务器获取用户信息
        DingTalkUser dingTalkUser = DingTalkUserUtil.getDingTalkUserByDingId(dingId,token);
        // 将数据库用户信息封装为UserDetails信息并返回
        return createUser(dingTalkUser);
    }

    private User createUser(DingTalkUser dingTalkUser){
        User user = new User();
        user.setUserId(null);
        user.setUsername(dingTalkUser.getMobile());
        user.setRealName(dingTalkUser.getName());
        user.setPassword(passwordEncoder.encode(VehicleConstant.USER_DEFAULT_PASSWORD));
        user.setDingId(dingTalkUser.getUserid());
        user.setUnionId(dingTalkUser.getUnionid());
        if (StringUtils.isBlank(dingTalkUser.getAvatar())){
            user.setAvatar("");
        }else {
            user.setAvatar(dingTalkUser.getAvatar());
        }
        user.setMobile(dingTalkUser.getMobile());
        user.setSysAdmin(false);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setCreateTime(new Date());
        user.setCreateUserId(VehicleConstant.ADMIN_USER_ID);
        user.setCreateUserName(VehicleConstant.ADMIN_USER_NAME);
        Long userId = saveUser(user);
        if (userId == null){
            throw new VehicleException(ExceptionCode.USER_INFO_SAVE_FAIL);
        }
        user.setUserId(userId);
        return user;
    }

    private Long saveUser(User user){
        if (user == null){
            throw new VehicleException(ExceptionCode.USER_INFO_IS_NULL);
        }
        userMapper.insert(user);
        return user.getUserId();
    }
}
