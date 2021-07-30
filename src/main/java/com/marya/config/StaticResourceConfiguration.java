package com.marya.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {
    @Value("${files.location}")
    private String location;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/**")
                .addResourceLocations(location);
//                .addResourceLocations("classpath:/src/main/webapp");
//                .addResourceLocations("/src/main/webapp/");
//                .setCachePeriod(0);
    }
}
