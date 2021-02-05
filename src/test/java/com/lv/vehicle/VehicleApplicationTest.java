package com.lv.vehicle;

import com.lv.vehicle.redis.RedisUtil;
import com.lv.vehicle.utils.TokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle
 * FileName: VehicleApplicationTest
 * Author: lv
 * Date: 2021/2/4 22:59
 * Description: 描述信息
 */
@SpringBootTest(classes = VehicleApplication.class)
@RunWith(SpringRunner.class)
public class VehicleApplicationTest {


    @Autowired
    private RedisUtil redisUtil;


    @Test
    public void redisTest(){
        String token = TokenUtil.getToken(redisUtil);
        System.out.println("token = " + token);
    }

}