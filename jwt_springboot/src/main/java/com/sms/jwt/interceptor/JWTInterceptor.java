package com.sms.jwt.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.jwt.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;
import java.util.HashMap;

/**
 * @author v-sunms.gd@chinatelecom.cn
 * @date 2021-06-15 10:00
 */
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        try {
            DecodedJWT decodedJWT = JWTUtils.verifyToken(token);
            map.put("code",1);
            map.put("msg","请求成功");
            return true;
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg","无效签名！！！！");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg","token过时！！！！！");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg","token算法不一致！！！！");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("msg","token无效！！！！");
        }
        map.put("code",0);
        map.put("status",false);
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(map);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().println(s);
        return false;
    }
}
