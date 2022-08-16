package com.backend.portfolio.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://181.231.74.169/")
                .allowCredentials(true)
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");

    }
}