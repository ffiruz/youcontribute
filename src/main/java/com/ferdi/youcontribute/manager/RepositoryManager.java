package com.ferdi.youcontribute.manager;

import com.ferdi.youcontribute.config.GithubProperties;
import com.ferdi.youcontribute.models.Issue;
import com.ferdi.youcontribute.models.Repository;
import com.ferdi.youcontribute.service.GithubClient;
import com.ferdi.youcontribute.service.IssueService;
import com.ferdi.youcontribute.service.RepositoryService;
import com.ferdi.youcontribute.service.models.GithubIssueResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RepositoryManager {

    //Transactional contexi olan bir servisi , Transactional context'i olmayan bir clientdan ayırabilmek için bu classı kullaıyoruz..
    // Bu class içerisinde farklı transactional işlemler var.Bu nedenle RepositoryManager classını oluşturarak burada yönettik.
    //Böylece Service leri transactional işlemlerini birbirden ayırdık.Farklı servisler farklı göervleri yapabilirler böylece.
    private final RepositoryService repositoryService;

    private final IssueService issueService;

    private final GithubClient githubClient;

    public void importRepository(String organization, String repository) {
        repositoryService.create(repository, organization);
    }

    //issue ları import ederken , Async(asink) yapıyı kullanacağız.

    @Async
    public void importIssues(Repository repository) {
        //Bu job çalışırken bir önceki günün issue larını almaya çalışacağız.Böylece dünden itibaren açılan issuelar ile sadece logic oalcak.
        //Instant bir anı, zaman çizgisindeki belirli bir noktayı temsil eder. · LocalDateTime tarih ve günün saatini temsil eder.

        LocalDate sinceYesterday = LocalDate.now().minus(1, ChronoUnit.DAYS);
        //  LocalDate sinceDate =  LocalDate.ofInstant Instant.now().minus(1, ChronoUnit.DAYS)

        GithubIssueResponse[] githubIssuesResponses = this.githubClient.listIssues(repository.getOrganization(),
                repository.getRepository(), sinceYesterday);
        List<Issue> issues = Arrays.stream(githubIssuesResponses).map(githubIssue -> Issue.builder().
                        title(githubIssue.getTitle()).body(githubIssue.getBody()).build())
                .collect(Collectors.toList());
        this.issueService.saveAll(issues);

    }
    //Batch şeklinde db ye insert edilecek.
    //Schedule config oluşturup , Dakikada bir schedule çalışacak .


    //Github client -> https://docs.github.com/en/rest/reference/issues


    /*
    Spring de uygulama ayağa kalktığında collection-pool denilen bir mekanizma ile çalışır.
    Bu proje için Hikari-Pool kullanıyoruz.
    Ne yapar ?
    Mesela uygulama içinde database ile balantı kuran birden fazla istemci  var.İstemciden gelen istekleri kontrolsüz database e bağlarsak , (mesela aynı anda 1000 atne isteği bağlan vs.)
    Bu durum iyi bir senaryo değildir.Bu nedenle connection-pool mekanizması vardır.Conenction-pool; Database e bağlanan istemcileri bir havuz da toplar ve gereli gördüğünde kuyruğa atar.
     */


}
