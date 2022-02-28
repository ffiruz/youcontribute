package com.ferdi.youcontribute.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {  //UI tarafından gelen istekleri crosOrigin ile alınmasını sağladık.

        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
    }
}
