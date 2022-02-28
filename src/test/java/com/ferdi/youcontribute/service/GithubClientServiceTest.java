package com.ferdi.youcontribute.service;

import com.ferdi.youcontribute.config.GithubProperties;
import com.ferdi.youcontribute.service.models.GithubIssueResponse;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.BDDAssertions.then;


@ContextConfiguration(initializers = GithubClientServiceTest.Initilazer.class) //Contexte  configuration tanımalama
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {GithubClient.class, RestTemplate.class, GithubProperties.class})
@EnableConfigurationProperties(value = GithubProperties.class)
public  class GithubClientServiceTest {
    //RestTempalte ile bağlandığımız github endpointi nin testlerini WireMock ile yapacağız.
    //Githuba bağlı olmadan bir json file dosyası oluşturduk.(__files/github/issues.json içerisinde)
    //Wiremock ile bu file üzerinden testlerimizi yaptık.

    @Autowired
    WireMockServer wireMockServer;


    @Autowired
    GithubClient githubClient;


    @Test
    public void it_should_list_issues() throws Exception {

        //given
        //__files/github/issues.json içerisinde ," {{gıthub_base}}/repos/octocat/Hello-World/issues" endpointinden dönen datayı kopyaladık.
        //Github'ın api sine gitmeden , sanki gidiyormuşuz gibi , test yapıyoruz.
        // Sanki postmanden bu datayı almışız gibi.->Burada "__files" wiremockun özel tanıdığı bir path .Bu nedenle ,withBodyFile("/github/issues.json") şeklidne kullabiliriz.
        wireMockServer.stubFor(get(urlPathEqualTo("/repos/octocat/Hello-World/issues")) //mockladık.
                        .withQueryParam("since",equalTo("2022-02-10"))
                        .withHeader("Authorization",equalTo("token tokenTest")) //token set ettik.
                .willReturn(aResponse().withBodyFile("/github/issues.json") //_files/github/issues.json içerisinde ki data dönecek.
                .withHeader("Content_Type", MediaType.APPLICATION_JSON_VALUE).withStatus(200)));

        //localhost:xxx/repos/octocat/Hello-World/issues endpointine bir istek attığımız da , biz buradan dönen datayı stub ile file dosyasında tanımladığımız json'ı verdik.

        //when

        GithubIssueResponse[] response= this.githubClient.listIssues("octocat","Hello-World", LocalDate.parse("2022-02-10"));


        //then
        // verify işlemi

        then(response).isNotNull(); //null değer gelmedğini verify ettik.
        then(response.length).isEqualTo(30); //kayıt sayısının 30 olduduğunu verify ettik.
        GithubIssueResponse githubIssueResponse=response[0];
        then(githubIssueResponse.getTitle()).isEqualTo("sentence case");


    }


    static class Initilazer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {

            WireMockServer wireMockServer = new WireMockServer(new WireMockConfiguration().dynamicPort()); //port al.
            wireMockServer.start();

            applicationContext.getBeanFactory().registerSingleton("wireMockServer",wireMockServer);

            applicationContext.addApplicationListener(applicationEvent -> {
                if(applicationEvent instanceof ContextClosedEvent)
                {
                    wireMockServer.stop();
                }
            });

            TestPropertyValues.of("github.apiurl=" + wireMockServer.baseUrl()
                    ,"github.token=tokenTest")
                    .applyTo(applicationContext.getEnvironment());

            //Sistem ayağa  kalkarken application.properties dosyasına müdahale ettik.github.apiurl ve githun.token değerlerine test için yeni değerler verdik.


        }
    }


}