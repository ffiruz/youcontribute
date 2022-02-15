package com.ferdi.youcontribute.config;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application")  //application.xxx gibi .parent tipini belirtiyoruz(->application)
@AllArgsConstructor
@NoArgsConstructor
@Data
public  class ApplicationProperties {

    private Integer importfrequency;  //application.importfrequency

}