package com.hebada.web;

import com.hebada.web.interceptor.LoginInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by paddy.luo on 2017/9/21.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    public static final String LOGIN_USER = "LOGIN_USER";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new LoginInterceptor());
    }
}
