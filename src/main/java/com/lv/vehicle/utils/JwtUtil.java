package com.lv.vehicle.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lv.vehicle.constant.VehicleConstant;
import com.lv.vehicle.exception.ExceptionCode;
import com.lv.vehicle.exception.VehicleException;
import com.lv.vehicle.security.vo.AuthUser;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

public class JwtUtil {


    public static String generateTokenExpireInSeconds(AuthUser authUser, PrivateKey privateKey, int expire) {
        String token;
        try {
            token = Jwts.builder()
                    .setId(authUser.getUserId().toString())//设置jwt
                    .setSubject(VehicleConstant.JWT_SUBJECT)//设置jwt主题
                    .setIssuedAt(new Date())//设置jwt签发日期
                    .setExpiration(DateTime.now().plusSeconds(expire).toDate()) // 过期时间
                    .claim(VehicleConstant.JWT_PAYLOAD_USER_KEY, new ObjectMapper().writeValueAsString(authUser))
                    .signWith(SignatureAlgorithm.RS512, privateKey)
                    .compact();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw  new VehicleException(ExceptionCode.GENERATE_TOKEN_FAIL);
        }
        return token;
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     */
    public static AuthUser getUserFromToken(String token, PublicKey publicKey) {
        Jws<Claims> claimsJws;
        try {
            claimsJws = parserToken(token, publicKey);
        }catch (SignatureException e){
            e.printStackTrace();
            throw new VehicleException(ExceptionCode.TOKEN_PARAS_FAIL);
        }catch (ExpiredJwtException e){
            e.printStackTrace();
            throw new VehicleException(ExceptionCode.TOKEN_IS_EXPIRED);
        }

        Claims body = claimsJws.getBody();
        String user =  body.get(VehicleConstant.JWT_PAYLOAD_USER_KEY, String.class);
        if (StringUtils.isBlank(user)){
            throw new VehicleException(ExceptionCode.JWT_TOKEN_IS_NULL);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        AuthUser authUser;
        try {
            authUser = objectMapper.readValue(user, AuthUser.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new VehicleException(ExceptionCode.TOKEN_PARAS_FAIL);
        }
        if (authUser == null){
            throw new VehicleException(ExceptionCode.TOKEN_PARAS_FAIL);
        }
        return authUser;
    }


    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }
}
