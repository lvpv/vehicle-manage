package com.lv.vehicle.constant;

/**
 * Project: vehicle-manage
 * Model: vehicle-manage
 * Package: com.lv.vehicle.constant
 * FileName: VehicleConstant
 * Author: lv
 * Date: 2021/2/4 21:34
 * Description: 描述信息
 */
public class VehicleConstant {

    public static final String GET_METHOD="GET";

    public static final String POST_METHOD="POST";

    public static final String AUTH_UUID_PARAM ="uuid";

    public static final String AUTH_CODE_PARAM ="code";

    public static final String DEFAULT_LOGIN_PATH ="/login";

    public static final String AUTH_OPTION_PATH ="/**";

    public static final String AUTH_LOGIN_PATH ="/auth/login/code";

    public static final String USER_DEFAULT_PASSWORD ="123456";

    public static final long ADMIN_USER_ID =1L;

    public static final String ADMIN_USER_NAME ="admin";

    public static final String JWT_SUBJECT ="vehicle-manage";

    public static final String JWT_PAYLOAD_USER_KEY ="auth_user";

//    public static final String AUTH_LOGIN_CODE_PATH ="/login/code";

    public static final String AUTH_CODE_PATH ="/auth/code";

    public static final String GLOBAL_REDIS_KEY_PREFIX="com:lv:vehicle:";

    public static final String TOKEN_REDIS_KEY=GLOBAL_REDIS_KEY_PREFIX + "dingtalk_token";

    public static final String VALID_CODE_REDIS_KEY=GLOBAL_REDIS_KEY_PREFIX + "valid_code:";

    public static final String LOGIN_USER_REDIS_KEY=GLOBAL_REDIS_KEY_PREFIX + "login_user:";

    public static final int VALID_CODE_REDIS_EXPIRATION = 60*5;

    public static final String CONTENT_TYPE="application/json";

    public static final String CONTENT_TYPE_UTF="application/json;charset=utf-8";


}
