package com.ferdi.youcontribute.service.models;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) //Aşağıda yazdığımız camel case formatta ki değişkenleri(örnek:htmlUrl),snakeCase(örnek:html_url) e çevirecek.Deserilaze işleminde.
@Data
public class GithubPullsResponse {

    private  String state;
    private User user;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static  class User
    {
        private String login;
    }
}
