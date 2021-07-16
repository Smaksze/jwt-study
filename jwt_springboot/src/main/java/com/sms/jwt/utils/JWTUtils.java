package com.sms.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author v-sunms.gd@chinatelecom.cn
 * @date 2021-06-11 16:34
 */
public class JWTUtils {

    // 盐
    private static final String SIGN = "!Q2w#E4r%T6y";

//    // 过期时间
//    private static final Date time;
//    static {
//        Calendar instance = Calendar.getInstance();
//        instance.set(Calendar.MINUTE,30); //30分钟过期
//        time = instance.getTime();
//    }

    /**
     * 获取token
     * @param map
     * @return
     */
    public static String getToken(Map<String,String> map){
        HashMap<String, Object> header = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,30);

        JWTCreator.Builder builder = JWT.create();
        //设置 payload
        map.forEach((k,v) ->{
            builder.withClaim(k,v);
        });
        //设置签名和过期时间
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256("QWER!@#$$%%"));
        return token;
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public static DecodedJWT verifyToken(String token){
        // 设置加密算法和盐
        Verification verification = JWT.require(Algorithm.HMAC256("QWER!@#$$%%"));
        JWTVerifier jwtVerifier = verification.build();
        DecodedJWT verify = jwtVerifier.verify(token);
        return verify;
    }

    /**
     * 获取JWT中的payload的值
     * @param token
     * @param key
     * @return
     */
    public static String getClaim(String token,String key){
        DecodedJWT decodedJWT = verifyToken(token);
        String value = decodedJWT.getClaim(key).asString();
        return value;
    }

    public static void main(String[] args) {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("userId","1");
//        map.put("username","zhangsan");
//        String token = getToken(map);
//        System.out.println(token);

        DecodedJWT decodedJWT = verifyToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MjM3MjA2MTYsInVzZXJJZCI6IjEiLCJ1c2VybmFtZSI6InpoYW5nc2FuIn0.6BjWtXGzzcBBOieQ5sOF-5mquL8LTsrqg37omz2WTeE");
    }

}
