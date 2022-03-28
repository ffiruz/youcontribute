package com.ferdi.youcontribute.controller;

import com.ferdi.youcontribute.controller.requests.GetGithubAccessTokenRequest;
import com.ferdi.youcontribute.controller.resources.GithubAccessTokenResponse;
import com.ferdi.youcontribute.service.GithubClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth/github")
@AllArgsConstructor
public class GithubAuthController {

    private final GithubClient githubClient;


    //İstek buraya gelecek ve buradan client_id alınıp ,redirect edilecek.
    //https://github.com/login/oauth/authorize?client_id=a78ef8b0473267ff0407 ->Normalde daha önce manuel olarak oluşturduğumuz endpointin, sonuna client_id=a78ef8b0473267ff0407 eklemesini burada yapacağız.
    //localhost:8090/auth/github/authorize  -> Buraya bir istek attığımızda : https://github.com/login/oauth/authorize?client_id=a78ef8b0473267ff0407 yöneltecek bir endpoint
    //https://github.com/login/oauth/authorize?client_id=a78ef8b0473267ff0407 bu endpoint ise ; Github üzerinde yaptığımzı konfigurasyon ile --> http://localhost:4200/auth/github/callback dönecek.
    @GetMapping("/authorize")
    public void authorize(HttpServletResponse response)
    {
        response.setHeader("Location",this.githubClient.getAuthorizeURL());
        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY); //302->code

    }

    //Hassas dataları taşırken request üzerinden taşımamak gerek.
    // Çünkü biz bu dataları taşırken Load balancer veya Nginx vs bir teknoloji üzerinden geçiyoruz.
    //Nginx gibi bazı server lar bu get issteklerini genmelde loglar.Bu hassas datalar için sakıncalı.
    //Çümkü loglanan kayıtlar merkezi bir yere gider ve müşteri datalarının güvenliği problem olur.
    //Nginx; yüksek eş zamanlı çalışma kabiliyeti, yüksek performans ve düşük hafıza kullanımına odaklanılarak tasarlanmış bir Web sunucusudur.
    //Bu nedenle RequestParam pek kullanmayız.
    @PostMapping("/access_token")
    public GithubAccessTokenResponse getAccessToken(@RequestBody GetGithubAccessTokenRequest request)
    {
        GithubAccessTokenResponse githubAccessTokenResponse=GithubAccessTokenResponse.builder().accessToken(this.githubClient.getAccessToken(request.getCode())).build();
        return githubAccessTokenResponse;

        //curl -XPOST -H 'Content-Type: application/json'  -d '{"code":"f059e994c5a5388e4483"}' http://localhost:8090/auth/github/access_token
        //Yukarıda ki post sorgusunda bize token dönecek.->_token=10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
        //curl -XGET -H "Authorization: token o_jaQQ6T0oq2bmvjV1E3CF2zajK4CLb71wqzdm" https://api.github.com/user
    }
}
