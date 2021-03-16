package com.awei.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new MyHandlerInterceptor()) //放入写好的拦截器
//                .addPathPatterns("/**")//拦截的请求
//                .excludePathPatterns("/","/index","/login","/css/**","/img/**","/js/**");//放行的请求
//    }
}
