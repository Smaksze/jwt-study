package jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;

/**
 * @author v-sunms.gd@chinatelecom.cn
 * @date 2021-06-11 16:05
 */
public class TestJwtToken {

    @Test
    public void testJwtCreate(){
        HashMap<String, Object> header = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE,30);
        String token = JWT.create()
                .withHeader(header) //头
                .withClaim("userId", 23)  //payload
                .withClaim("username", "zhangsan")//payload
                .withExpiresAt(instance.getTime()) //过期时间
                .sign(Algorithm.HMAC256("QWER!@#$$%%"));// sign
        System.out.println(token);


    }

    @Test
    public void testJwtRequire(){
        //创建验证对象
        Verification verification = JWT.require(Algorithm.HMAC256("QWER!@#$$%%"));
        JWTVerifier jwtVerifier = verification.build();

        DecodedJWT decodedJWT = jwtVerifier
                .verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MjM3MjgxNjYsInVzZXJJZCI6MjMsInVzZXJuYW1lIjoiemhhbmdzYW4ifQ.d1KYCxtRC2YIysF6rqqZoLXQrpVHNu96aylNPLxU9U4");
        System.out.println(decodedJWT);
        System.out.println(decodedJWT.getClaim("userId").asInt());
        System.out.println(decodedJWT.getClaim("username").asString());

    }
}
