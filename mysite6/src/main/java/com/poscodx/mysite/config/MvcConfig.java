package com.poscodx.mysite.config;

import com.poscodx.mysite.event.ApplicationContextEventListener;
import com.poscodx.mysite.interceptor.SiteInterceptor;
import com.poscodx.mysite.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;

@SpringBootConfiguration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {
    private final SiteService siteService;
    private final ApplicationContext applicationContext;

    // View Resolver
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setExposeContextBeansAsAttributes(true);
        viewResolver.setExposedContextBeanNames("site");

        return viewResolver;
    }

    // Site Interceptor
    @Bean
    public HandlerInterceptor siteInterceptor() {
        return new SiteInterceptor(siteService, localeResolver());
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

    // Locale Resolver
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieName("lang");
        localeResolver.setCookieHttpOnly(false);
        return localeResolver;
    }
}