package com.ferdi.youcontribute.service.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GithubAccessTokenRequest {

    @JsonProperty("client_id") //Normalde bu anatasyonu yazmasak , burayı  clientId olarak tanıyacak.Ancak biz bunu client_id olarak tanıtmamız gerekdi.
    private String clientId;
    @JsonProperty("client_secret")
    private String clientSecret;
    private String code;

}
