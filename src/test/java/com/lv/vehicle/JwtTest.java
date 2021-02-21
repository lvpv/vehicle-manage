package com.lv.vehicle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;

@SpringBootTest(classes = VehicleApplication.class)
@RunWith(SpringRunner.class)
public class JwtTest {

    @Autowired
    private ObjectMapper objectMapper;


    @Resource(name = "keyProp")
    private KeyProperties keyProperties;

    @Test
    public void testTokenUtil(){
//        KeyProperties.KeyStore keyStore = keyProperties.getKeyStore();
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore.getLocation(),keyStore.getSecret().toCharArray());
//        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(keyStore.getAlias());
//        PrivateKey privateKey = keyPair.getPrivate();
//        AuthUser authUser = new AuthUser();
//        authUser.setUserId(1L);
//        authUser.setDingId("123456");
//        authUser.setUsername("18999233076");
//        authUser.setRealName("话题废");
//
//        String token = JwtUtil.generateTokenExpireInSeconds(authUser, privateKey, 5);
//        System.out.println("token = " + token);

    }

    @Test
    public void validToken(){
//        KeyProperties.KeyStore keyStore = keyProperties.getKeyStore();
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore.getLocation(),keyStore.getSecret().toCharArray());
//        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(keyStore.getAlias());
//        PublicKey publicKey = keyPair.getPublic();
//        String token = "eyJhbGciOiJSUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoidmVoaWNsZS1tYW5hZ2UiLCJpYXQiOjE2MTMwNDY4ODgsImV4cCI6MTYxMzA0Njg5MywiYXV0aF91c2VyIjoie1widXNlcklkXCI6MSxcInVzZXJuYW1lXCI6XCIxODk5OTIzMzA3NlwiLFwiZGluZ0lkXCI6XCIxMjM0NTZcIixcInJlYWxOYW1lXCI6XCLor53popjlup9cIn0ifQ.GU6EWLTjIFAL4stE141iifDfhup3R336Fr5YwuTruOFYLrGx2USUbqVNeTbE61RK4wfv-k95I3BLRu_16Kd6_SQoRnZgN3CLAIk4RSUiL1fia90h9-WtPCcFecgMh4i_cJsFeHMng31-3ShQ7LH_gOBrmfKXjOc0GEUWXZJQI7_v4J3j4ivpML4BvewWhY4afGbsoAiFoGg5xn_MsAG_juoaKD7-MXPImWl_rZyV_qNXbwXHXNRMKE0rdd54tmeWi8KOqr_5R17a4kpaWkZw19Z-o0TuXv0pxD2FKFCgFYzode2gQj56uYiAuEU9ZKFtZ5Y45N-9GUagQXCAsAd4dA";
//        AuthUser user = JwtUtil.getUserFromToken(token, publicKey);
//        System.out.println("user = " + user);
    }

    @Test
    public void createJwt() throws JsonProcessingException, UnsupportedEncodingException {


        KeyProperties.KeyStore keyStore = keyProperties.getKeyStore();

        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keyStore.getLocation(),keyStore.getSecret().toCharArray());

        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(keyStore.getAlias());
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // Jwt jwt = JwtHelper.encode(JSON.toJSONString(tokenMap), new RsaSigner(rsaPrivate));
        PublicKey aPublic = keyPair.getPublic();
        byte[] encoded = aPublic.getEncoded();
        String s = new String(encoded, StandardCharsets.UTF_8);
        System.out.println("s = " + s);
//        //自定义Payload
//        Map<String, Object> tokenMap = new HashMap<>();
//        tokenMap.put("id", "1");
//        tokenMap.put("name", "itheima");
//        tokenMap.put("roles", "ROLE_VIP,ROLE_USER");
//
//        //生成Jwt令牌
        //Jwt jwt = JwtHelper.encode(objectMapper.writeValueAsString(tokenMap), new RsaSigner(privateKey));
//        //JwtHelper.encode(objectMapper.writeValueAsString(tokenMap),new RsaSigner(privateKey),)
//
//        //取出令牌
//        String encoded = jwt.getEncoded();
//        System.out.println(encoded);

        RsaSigner rsaSigner = new RsaSigner(privateKey);

    }

    /***
     * 校验令牌
     */
    @Test
    public void testParseToken(){
        //令牌
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlcyI6IlJPTEVfVklQLFJPTEVfVVNFUiIsIm5hbWUiOiJpdGhlaW1hIiwiaWQiOiIxIn0.Kk6BO6NP3PiMSDAQ8fOMVOEypPYZzoBCxDipEk0HtSS0HHHGF3kYxXL4RWETwXrMRnZxOxIIW1Uisw9IN5crntXXLrLhrwwvUff04eLjVLlP0EBmOUpc5I3yyAQl2n4XkmXPmq3Yp42Tg81Tu-H_IkLdwni3zh5WOq8DPf7uCn_DQLlcykNs1acR7exaQ3FM1Vkxq2tyHt35H3jE23bXLYndckXOZm9D5y-5JPJ4J1aWitWAMf-waPrc568niKN_sXtLiDdE9Yd_Q8CAockz7LaqCWWPMHc0JGFNzgwFKMkJIP4vEiG4a-tZ21uNoTH3cWxYdvjqXfftfto9Jswj7w";
        //公钥
        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlMFmbAcnf+TtG+aNWgAfqdPwCVvYOthd60DqahsHbQuILlMAqQ6o0TJGBbNW8sVLuTgDGtkiqZ2FtGxu0dJKUQ/IAyQTsXduZw8UXX0hDLfH8DIFrK9G0zJTmlUaTY2/T5mEemvyDqGxdhgqImfgPpxWtLpCSQuX9GCN4N1urtBYRy11z0L99tlEirNzDJDuzX2mFtL2y3WyicobprYyDV/EI8Ca9+Hnq3nF76JdkkE3/I+/afQX59gvZqB4uZ+CCJNEJM3TN3j5uRTBmDEV9OH7dtmyeyXt8pmfBikOg+I+T7zmhH+/E/tqH5+FUDNLXvGzTSM5J1hbgKCFVlG4RwIDAQAB-----END PUBLIC KEY-----";

        //校验Jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));

        //获取Jwt原始内容 载荷
        String claims = jwt.getClaims();
        System.out.println(claims);
        //jwt令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }
}
