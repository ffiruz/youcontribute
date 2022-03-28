package com.ferdi.youcontribute.service.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GithubAccessTokenResponse {

    //Normalde bizim response objemiz de iki alan daha , api dan dönüyor.Ancak bunu eklemedik.Çünkü şuan için işimize yaramıyor.
    //Spring de bunu deserialze ederken , buraya set etmediğimiz fieldları arayacak.Ancak bulamayacak.
    //Bu nedenle  --> @JsonInclude(JsonInclude.Include.NON_NULL) kullandık-->NON_NULL olanları include etme diyoruz.

    @JsonProperty("access_token")
    private String accessToken;

}
