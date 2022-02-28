package com.ferdi.youcontribute.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("github")  //github.token gibi .parent tipini belirtiyoruz(->github)
@AllArgsConstructor
@NoArgsConstructor
@Data
public  class GithubProperties { //Application properties deki ayarları , GithubProperties  objesine set etmek için bu classı kullanacağız
    private  String token;              //Token değerini set edeceğiz.


    private String apiUrl;


}