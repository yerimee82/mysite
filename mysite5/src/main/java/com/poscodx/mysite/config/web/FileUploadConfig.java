package com.poscodx.mysite.config.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:com/poscodx/mysite/config/web/fileupload.properties")
@RequiredArgsConstructor
public class FileUploadConfig implements WebMvcConfigurer {
    private final Environment env;
    // Multipart Resolver
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(env.getProperty("fileupload.maxUploadSize", Long.class));
        multipartResolver.setMaxInMemorySize(env.getProperty("fileupload.maxInMemorySize", Integer.class));
        multipartResolver.setDefaultEncoding(env.getProperty("fileupload.defaultEncoding"));

        return multipartResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(env.getProperty("fileupload.resourceUrl") + "/**")
                .addResourceLocations("file:" + env.getProperty("fileupload.uploadLocation") + "/");
    }


}
