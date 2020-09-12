package com.cqupt.demo.config;

import com.cqupt.demo.utils.loginHandlerInterceptor;
import com.cqupt.demo.utils.powerHanlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class mvcConfiger implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new loginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/api/login","/api/adlogin","/error/**","/api/user","/api/admin","/hls/**");
        registry.addInterceptor(new powerHanlerInterceptor()).addPathPatterns("/api/upload","/api/delete");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/movie/**").addResourceLocations("file:/home/ray/movie/");
    }



}
