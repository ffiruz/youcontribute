package com.ferdi.youcontribute.service;

import com.ferdi.youcontribute.config.GithubProperties;
import com.ferdi.youcontribute.service.models.GithubIssueResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class GithubClient { //client oluşturacağız

    public final RestTemplate restTemplate;

    private final GithubProperties githubProperties;



    public GithubIssueResponse[] listIssues(String owner, String repository) //GithubIssueResponse tipinde bir array dönüyor.
    {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","token "+githubProperties.getToken());
        HttpEntity request= new HttpEntity(headers);
         String issueUrl=String.format("%s/repos/%s/%s/issues",this.githubProperties.getApiUrl(),owner,repository);     //-->BaseUrl/repos/vmg/redcarpet/issues ->BaeUrl:application.propertys içinden set edilecek.
        //%s ile belirttiğimiz parametre de dinamik olarak gelecek alanlarımız.
        ResponseEntity<GithubIssueResponse[]> response=this.restTemplate.exchange(issueUrl, HttpMethod.GET,request, GithubIssueResponse[].class); //issueUrl e , GET isteği at.Requesti de gönder.Response tipini belirt.
        return response.getBody();
    }



}
