package com.sms.jwt.config;

import com.sms.jwt.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author v-sunms.gd@chinatelecom.cn
 * @date 2021-06-15 10:10
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/user/test") //拦截
                .excludePathPatterns("/user/login"); //放行
    }
}
