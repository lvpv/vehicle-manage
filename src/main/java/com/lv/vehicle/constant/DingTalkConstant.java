package com.lv.vehicle.constant;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.constant
 * FileName: DingTalkConstant
 * Author: lv
 * Date: 2021/2/4 21:32
 * Description: 描述信息
 */
public class DingTalkConstant {

    // appKey
    public static final String APPKEY = "xxxxxxxxxxxxxxx";

    // appSecret
    public static final String APPSECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    // SSOSecret
    public static final String SSOSecret = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    // 获取管理后台免登的code
    public static final String ADMIN_CODE = "48e77478df363fd4b2909612789ecffc";

    // 获取Token地址
    public static final String URL_GET_TOKEN = "https://oapi.dingtalk.com/gettoken";

    // 获取用户ID地址
    public static final String URL_GET_USER_ID = "https://oapi.dingtalk.com/topapi/v2/user/getuserinfo";

    // 获取用户信息地址
    public static final String URL_GET_USER_INFO = "https://oapi.dingtalk.com/topapi/v2/user/get";

    // 钉钉api请求语言参数(中文)
    public static final String DING_TALK_LANGUAGE ="zh_CN";

    // 请求访问成功返回状态码
    public static final int OK_CODE = 0;

    // 请求成功返回响应信息
    public static final String OK_MESSAGE = "ok";
}
