package com.poscodx.mysite.config.web;

import com.poscodx.mysite.security.AuthInterceptor;
import com.poscodx.mysite.security.AuthUserHandlerMethodArgumentResolver;
import com.poscodx.mysite.security.LoginInterceptor;
import com.poscodx.mysite.security.LogoutInterceptor;
import com.poscodx.mysite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class SecurityConfig implements WebMvcConfigurer {
    private final UserService userService;
    // Argument Resolver
    @Bean
    public HandlerMethodArgumentResolver handlerMethodArgumentResolver() {
        return new AuthUserHandlerMethodArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(handlerMethodArgumentResolver());
    }

    // Interceptors
    @Bean
    public HandlerInterceptor loginInterceptor() {
        return new LoginInterceptor(userService);
    }

    @Bean
    public HandlerInterceptor logoutInterceptor() {
        return new LogoutInterceptor();
    }

    @Bean
    public HandlerInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(loginInterceptor())
                .addPathPatterns("/user/auth");

        registry
                .addInterceptor(logoutInterceptor())
                .addPathPatterns("/user/logout");

        registry
                .addInterceptor(authInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/auth", "/user/logout", "/assets/**");
    }
}