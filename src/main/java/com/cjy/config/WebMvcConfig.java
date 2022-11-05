package com.cjy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    告知系统imges 当成 静态资源访问

        String path = System.getProperty("user.dir") + "\\src\\main\\webapp\\imges\\";
        registry.addResourceHandler("/imges/**").addResourceLocations("classpath:/imges/");
        registry.addResourceHandler("/imges/**").addResourceLocations("file:"+path);
    }
}

