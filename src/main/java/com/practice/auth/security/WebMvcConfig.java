package com.practice.auth.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private JWTInterceptor jwtInterceptor;

    public WebMvcConfig(JWTInterceptor jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Here you can add more paths that you want to secure and you can exclude any that you want to leave open.
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/hello").excludePathPatterns("/auth/**");
    }
}

