package com.lv.vehicle.security.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "vehicle.jwt")
public class JwtProperties {

    private Integer tokenExpireSecond = 7200 ; // 两个小时(秒)

    private String authHeader = "Authorization";

    private String startWith = "Bearer ";

    private Integer refreshTokenExpireSecond = 604800; // 65535


    public void setTokenExpireSecond(Integer tokenExpireSecond) {
        if (tokenExpireSecond == null ||tokenExpireSecond<= 0){
            this.tokenExpireSecond = 7200;
        }else {
            this.tokenExpireSecond = tokenExpireSecond;
        }

    }

    public void setAuthHeader(String authHeader) {
        if (StringUtils.isBlank(authHeader)){
            this.authHeader = "Authorization";
        }else {
            this.authHeader = authHeader;
        }

    }

    public void setStartWith(String startWith) {
        if (StringUtils.isBlank(startWith)){
            this.startWith = startWith;
        }else {
            this.startWith = "Bearer ";
        }

    }

    public void setRefreshTokenExpireSecond(Integer refreshTokenExpireSecond) {
        if (refreshTokenExpireSecond == null || refreshTokenExpireSecond <= 0){
            this.refreshTokenExpireSecond = 604800;
        }else {
            this.refreshTokenExpireSecond = refreshTokenExpireSecond;
        }

    }

    public Integer getTokenExpireSecond() {
        return tokenExpireSecond;
    }

    public String getAuthHeader() {
        return authHeader;
    }

    public String getStartWith() {
        return startWith;
    }

    public Integer getRefreshTokenExpireSecond() {
        return refreshTokenExpireSecond;
    }
}
