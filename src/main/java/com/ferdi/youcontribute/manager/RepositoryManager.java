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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RepositoryManager {

    //Transactional contexi olan bir servisi , Transactional context'i olmayan bir clientdan ayırabilmek için bu classı kullaıyoruz..
    // Bu class içerisinde farklı transactional işlemler var.Bu nedenle RepositoryManager classını oluşturarak burada yönettik.
    //Böylece Service leri transactional işlemlerini birbirden ayırdık.
    private final RepositoryService repositoryService;

    private final IssueService issueService;

    private final GithubClient githubClient;

    public void importRepository(String organization,String repository)
    {
        repositoryService.create(repository,organization);
    }

    //issue ları import ederken , Async(asink) yapıyı kullanacağız.

    @Async
    public void importIssues(Repository repository)
    {
        GithubIssueResponse[] githubIssuesResponses=this.githubClient.listIssues(repository.getOrganization(),repository.getRepository());
        List<Issue> issues=Arrays.stream(githubIssuesResponses).map(githubIssue->new Issue())
                .collect(Collectors.toList());
        this.issueService.saveAll(issues);

    }
    //Batch şeklinde db ye insert edilecek.
    //Schedule config oluşturup , Dakikada bir schedule çalışacak .


}
