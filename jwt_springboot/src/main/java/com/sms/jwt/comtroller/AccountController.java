package com.sms.jwt.comtroller;

import com.auth0.jwt.JWT;
import com.sms.jwt.entity.Account;
import com.sms.jwt.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author v-sunms.gd@chinatelecom.cn
 * @date 2021-06-15 9:42
 */
@Controller
@Slf4j
public class AccountController {

    @GetMapping("/user/login")
    @ResponseBody
    public String login(Account account){
        log.info("用户名：[{}]",account.getUsername());
        log.info("密码：[{}]",account.getPassword());

        //todo 认证账号密码阶段，可使用shiro认证也可常规认证

        try {
            HashMap<String, String> payload = new HashMap<>();
            payload.put("username",account.getUsername());
            String token = JWTUtils.getToken(payload);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return "认证失败";
        }
    }


    @PostMapping("/user/test")
    @ResponseBody
    public Map<String,Object> test(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("status",true);
        map.put("msg","请求成功");
        return  map;
    }



}
