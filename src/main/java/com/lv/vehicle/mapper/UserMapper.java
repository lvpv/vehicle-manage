package com.lv.vehicle.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lv.vehicle.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.mapper
 * FileName: UserMapper
 * Author: lv
 * Date: 2021/2/6 20:49
 * Description: 描述信息
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    User loadUserByUsername(@Param("username") String username);

    User findUserByDingId(@Param("dingId")String dingId);


}
