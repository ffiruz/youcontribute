package com.ferdi.youcontribute.service;

import com.ferdi.youcontribute.config.GithubProperties;
import com.ferdi.youcontribute.service.models.GithubIssueResponse;
import com.ferdi.youcontribute.service.models.GithubPullsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class GithubClient { //client oluşturacağız

    public final RestTemplate restTemplate;

    private final GithubProperties githubProperties;



    public GithubIssueResponse[] listIssues(String owner, String repository, LocalDate since) //GithubIssueResponse tipinde bir array dönüyor.
    {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);


        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","token "+githubProperties.getToken().trim());  //Enviroment variables da -> githubProperties.getToken() set edildi.
        HttpEntity request= new HttpEntity(headers);
         String issueUrl=String.format("%s/repos/%s/%s/issues?since=%s",this.githubProperties.getApiUrl(),owner,repository,since.toString());     //-->BaseUrl/repos/vmg/redcarpet/issues?since=date ->BaeUrl:application.propertys içinden set edilecek.
        //%s ile belirttiğimiz parametre de dinamik olarak gelecek alanlarımız.


        ResponseEntity<GithubIssueResponse[]> response=this.restTemplate.exchange(issueUrl, HttpMethod.GET,request, GithubIssueResponse[].class); //issueUrl e , GET isteği at.Requesti de gönder.Response tipini belirt.
        return response.getBody();
    }

    public GithubPullsResponse[] listPullRequest(String owner, String repository) //GithubIssueResponse tipinde bir array dönüyor.
    {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);


        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","token "+githubProperties.getToken().trim());  //Enviroment variables da -> githubProperties.getToken() set edildi.
        HttpEntity request= new HttpEntity(headers);
        String pullRequestUrl=String.format("%s/repos/%s/%s/pulls",this.githubProperties.getApiUrl(),owner,repository);     //-->BaseUrl/repos/vmg/redcarpet/issues?since=date ->BaeUrl:application.propertys içinden set edilecek.
        //%s ile belirttiğimiz parametre de dinamik olarak gelecek alanlarımız.


        ResponseEntity<GithubPullsResponse[]> response=this.restTemplate.exchange(pullRequestUrl, HttpMethod.GET,request, GithubPullsResponse[].class); //issueUrl e , GET isteği at.Requesti de gönder.Response tipini belirt.
        return response.getBody();
    }



}
