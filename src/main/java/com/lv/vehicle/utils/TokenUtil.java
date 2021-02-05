package com.lv.vehicle.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.lv.vehicle.constant.DingTalkConstant;
import com.lv.vehicle.constant.VehicleConstant;
import com.lv.vehicle.exception.ExceptionCode;
import com.lv.vehicle.exception.VehicleException;
import com.lv.vehicle.redis.RedisUtil;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.utils
 * FileName: TokenUtil
 * Author: lv
 * Date: 2021/2/4 21:33
 * Description: 描述信息
 */
@Slf4j
public class TokenUtil {

    public static String getToken(RedisUtil redisUtil) {
        String token = (String) redisUtil.get(VehicleConstant.TOKEN_REDIS_KEY);
        if (StringUtils.isNotBlank(token)){
            return token;
        }
        DefaultDingTalkClient client = new DefaultDingTalkClient(DingTalkConstant.URL_GET_TOKEN);
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(DingTalkConstant.APPKEY);
        request.setAppsecret(DingTalkConstant.APPSECRET);
        request.setHttpMethod(VehicleConstant.GET_METHOD);
        OapiGettokenResponse response;
        try {
            response = client.execute(request);
            String accessToken = response.getAccessToken();
            Long errCode = response.getErrcode();
            String errMsg = response.getErrmsg();
            if (StringUtils.isBlank(accessToken)
                    || errCode != DingTalkConstant.OK_CODE
                    || !StringUtils.equals(errMsg, DingTalkConstant.OK_MESSAGE)) {
                log.error(ExceptionCode.GET_TOKEN_FAIL.message() + "：{}", errMsg);
                throw new VehicleException(ExceptionCode.GET_TOKEN_FAIL);
            }
            Long expiresIn = response.getExpiresIn();
            redisUtil.set(VehicleConstant.TOKEN_REDIS_KEY, accessToken, expiresIn);
            return accessToken;
        } catch (ApiException e) {
            e.printStackTrace();
            throw new VehicleException(ExceptionCode.GET_TOKEN_FAIL);
        }
    }
}
