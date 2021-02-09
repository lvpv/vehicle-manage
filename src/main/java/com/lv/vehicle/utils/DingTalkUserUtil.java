package com.lv.vehicle.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiV2UserGetRequest;
import com.dingtalk.api.request.OapiV2UserGetuserinfoRequest;
import com.dingtalk.api.response.OapiV2UserGetResponse;
import com.dingtalk.api.response.OapiV2UserGetuserinfoResponse;
import com.lv.vehicle.constant.DingTalkConstant;
import com.lv.vehicle.constant.VehicleConstant;
import com.lv.vehicle.exception.ExceptionCode;
import com.lv.vehicle.exception.VehicleException;
import com.lv.vehicle.security.vo.DingTalkUser;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class DingTalkUserUtil {

    public static String getUserDingId(String code, String token) {
        DingTalkClient client = new DefaultDingTalkClient(DingTalkConstant.URL_GET_USER_ID);
        OapiV2UserGetuserinfoRequest request = new OapiV2UserGetuserinfoRequest();
        request.setCode(code);
        request.setHttpMethod(VehicleConstant.GET_METHOD);
        OapiV2UserGetuserinfoResponse response;
        try {
            response = client.execute(request, token);
            Long errCode = response.getErrcode();
            String errMsg = response.getErrmsg();
            OapiV2UserGetuserinfoResponse.UserGetByCodeResponse result = response.getResult();
            if (result == null || errCode != DingTalkConstant.OK_CODE
                    || !StringUtils.equals(errMsg, DingTalkConstant.OK_MESSAGE)) {
                log.error(ExceptionCode.GET_DING_TALK_USER_ID_FAIL.message() + "：{}", errMsg);
                throw new VehicleException(ExceptionCode.GET_DING_TALK_USER_ID_FAIL);
            }
            if (StringUtils.isBlank(result.getUserid())){
                throw new VehicleException(ExceptionCode.GET_DING_TALK_USER_ID_FAIL);
            }
            return result.getUserid();
        } catch (ApiException e) {
            e.printStackTrace();
            throw new VehicleException(ExceptionCode.GET_DING_TALK_USER_ID_FAIL);
        }
    }

    public static DingTalkUser getDingTalkUserByDingId(String dingId,String token){
        DingTalkClient client = new DefaultDingTalkClient(DingTalkConstant.URL_GET_USER_INFO);
        OapiV2UserGetRequest req = new OapiV2UserGetRequest();
        req.setUserid(dingId);
        req.setLanguage(DingTalkConstant.DING_TALK_LANGUAGE);
        OapiV2UserGetResponse response;
        try {
            response = client.execute(req, token);
            OapiV2UserGetResponse.UserGetResponse result = response.getResult();
            Long errCode = response.getErrcode();
            String errMsg = response.getErrmsg();
            if (result == null || errCode != DingTalkConstant.OK_CODE
                    || !StringUtils.equals(errMsg, DingTalkConstant.OK_MESSAGE)) {
                log.error(ExceptionCode.GET_DING_TALK_USER_INFO_FAIL.message() + "：{}", errMsg);
                throw new VehicleException(ExceptionCode.GET_DING_TALK_USER_INFO_FAIL);
            }
            if (StringUtils.isAllBlank(result.getUserid(),result.getName(),result.getMobile())){
                throw new VehicleException(ExceptionCode.GET_DING_TALK_USER_INFO_FAIL);
            }
            return createDingTalkUser(result);
        } catch (ApiException e) {
            e.printStackTrace();
            throw new VehicleException(ExceptionCode.GET_DING_TALK_USER_INFO_FAIL);
        }
    }

    private static DingTalkUser createDingTalkUser(OapiV2UserGetResponse.UserGetResponse result){
        DingTalkUser dingTalkUser = new DingTalkUser();
        dingTalkUser.setUserid(result.getUserid());
        dingTalkUser.setUnionid(result.getUnionid());
        dingTalkUser.setName(result.getName());
        dingTalkUser.setAvatar(result.getAvatar());
        dingTalkUser.setMobile(result.getMobile());
        dingTalkUser.setJob_number(result.getJobNumber());
        dingTalkUser.setTitle(result.getTitle());
        dingTalkUser.setEmail(result.getEmail());
        dingTalkUser.setDept_id_list(result.getDeptIdList());
        dingTalkUser.setHired_date(result.getHiredDate());
        dingTalkUser.setAdmin(result.getAdmin());
        dingTalkUser.setBoss(result.getBoss());
        dingTalkUser.setLeader_in_dept(result.getLeaderInDept());
        dingTalkUser.setRole_list(result.getRoleList());
        return dingTalkUser;
    }
}
