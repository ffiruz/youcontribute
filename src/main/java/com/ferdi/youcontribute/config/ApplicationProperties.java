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

    private Integer challengeFrequency;  //application.importfrequency

    private OneSignalProperties oneSignal;
    //Application.properties içinde her . dediğimiz de bir obje aslında->application.one-signal.api-auth-key
    //application.one-signal.api-auth-key->applicarion ->parent class, one-signal->static olan class

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static  class OneSignalProperties
    {
        private String apiId;
        private String apiAuthKey;
        private  String newChallengeTemplateId;

    }


}