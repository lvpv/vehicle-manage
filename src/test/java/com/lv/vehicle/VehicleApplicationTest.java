package com.lv.vehicle;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiUserGetuserinfoRequest;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import com.lv.vehicle.redis.RedisUtil;
import com.lv.vehicle.utils.TokenUtil;
import com.taobao.api.ApiException;
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
        // 获取用户信息
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/getuserinfo");
        OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
        request.setCode("3fed1fb71eb0313aaa420ac209a33af8");
        request.setHttpMethod("GET");
        OapiUserGetuserinfoResponse response = null;
        try {
            response = client.execute(request, token);
        } catch (ApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 查询得到当前用户的userId
        // 获得到userId之后应用应该处理应用自身的登录会话管理（session）,避免后续的业务交互（前端到应用服务端）每次都要重新获取用户身份，提升用户体验
        assert response != null;
        String userId = response.getUserid();
        System.out.println("userId = " + userId);
    }

}