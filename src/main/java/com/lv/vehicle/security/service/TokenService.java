package com.lv.vehicle.security.service;

import com.lv.vehicle.constant.VehicleConstant;
import com.lv.vehicle.exception.AuthException;
import com.lv.vehicle.exception.ExceptionCode;
import com.lv.vehicle.exception.VehicleException;
import com.lv.vehicle.redis.RedisUtil;
import com.lv.vehicle.security.common.JwtProperties;
import com.lv.vehicle.security.vo.AuthUser;
import com.lv.vehicle.utils.RsaUtil;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class TokenService {

    private final KeyProperties keyProperties;

    private final JwtProperties jwtProperties;

    private final RedisUtil redisUtil;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    public TokenService(KeyProperties keyProperties, JwtProperties jwtProperties, RedisUtil redisUtil) {
        this.keyProperties = keyProperties;
        this.jwtProperties = jwtProperties;
        this.redisUtil = redisUtil;
    }


    public String generateToken(AuthUser authUser) {
        PrivateKey privateKey = RsaUtil.getPrivateKey(keyProperties);
        if (privateKey == null) {  // 获取私钥失败
            throw new AuthException(ExceptionCode.PRIVATE_KEY_GET_FAIL);
        }
        String token = UUID.randomUUID().toString(); // 生成token
        authUser.setToken(token);
        refreshToken(authUser); // 缓存登陆用户信息
        Map<String, Object> claims = new HashMap<>();
        claims.put(VehicleConstant.JWT_PAYLOAD_USER_KEY, token);
        return Jwts.builder()
                .setId(token)//设置jwt
                .setSubject(VehicleConstant.JWT_SUBJECT)//设置jwt主题
                .setIssuedAt(new Date())//设置jwt签发日期
                //.setExpiration(DateTime.now().plusSeconds(jwtProperties.getTokenExpireSecond()).toDate()) // 过期时间
                .setClaims(claims)
                .signWith(SignatureAlgorithm.RS512, privateKey)
                .compact();
    }

    public void refreshToken(AuthUser authUser) {
        authUser.setLoginTime(System.currentTimeMillis());
        authUser.setExpireTime(authUser.getLoginTime() + jwtProperties.getTokenExpireSecond() * 1000);
        // 根据uuid将loginUser缓存
        String key = getTokenKey(authUser.getToken());
        redisUtil.set(key, authUser, jwtProperties.getTokenExpireSecond());
    }

    private String getTokenKey(String uuid) {
        return VehicleConstant.LOGIN_USER_REDIS_KEY + uuid;
    }


    /**
     * 获取token中的用户信息
     *
     * @param token 用户请求中的令牌
     * @return 用户信息
     */
    public AuthUser getUserByToken(String token) {
        PublicKey publicKey = RsaUtil.getPublicKey(keyProperties);
        if (publicKey == null) {
            throw new AuthException(ExceptionCode.PUBLIC_KEY_GET_FAIL);
        }
        Jws<Claims> claimsJws;
        try {
            claimsJws = parserToken(token, publicKey);
        } catch (SignatureException | MalformedJwtException e) {
            e.printStackTrace();
            throw new VehicleException(ExceptionCode.TOKEN_PARAS_FAIL);
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            throw new VehicleException(ExceptionCode.TOKEN_IS_EXPIRED);
        }

        Claims body = claimsJws.getBody();
        String uuid = (String) body.get(VehicleConstant.JWT_PAYLOAD_USER_KEY);
        if (StringUtils.isBlank(uuid)) {
            throw new VehicleException(ExceptionCode.JWT_TOKEN_IS_NULL);
        }
        String key = getTokenKey(uuid);
        return (AuthUser) redisUtil.get(key);
    }


    public void verifyToken(AuthUser authUser) {
        long expireTime = authUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN) {
            refreshToken(authUser);
        }
    }


    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }
}
