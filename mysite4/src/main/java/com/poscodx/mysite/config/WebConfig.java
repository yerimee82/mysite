package com.poscodx.mysite.config;

import com.poscodx.mysite.config.web.FileUploadConfig;
import com.poscodx.mysite.config.web.LocaleConfig;
import com.poscodx.mysite.config.web.MvcConfig;
import com.poscodx.mysite.config.web.SecurityConfig;
import com.poscodx.mysite.event.ApplicationContextEventListener;
import com.poscodx.mysite.interceptor.SiteInterceptor;
import com.poscodx.mysite.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
@EnableAspectJAutoProxy
@Import({MvcConfig.class, LocaleConfig.class, SecurityConfig.class, FileUploadConfig.class})
@ComponentScan({"com.poscodx.mysite.controller", "com.poscodx.mysite.exception"})
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final SiteService siteService;
    private final LocaleResolver localeResolver;
    private final ApplicationContext applicationContext;

    // Site Interceptor
    @Bean
    public HandlerInterceptor siteInterceptor() {
        return new SiteInterceptor(siteService, localeResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(siteInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/assets/**");
    }

    // ApplicationContext Event Listener
    @Bean
    public ApplicationContextEventListener applicationContextEventListener() {
        return new ApplicationContextEventListener(applicationContext);
    }
}