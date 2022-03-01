package com.ferdi.youcontribute.schedulers;

import com.ferdi.youcontribute.client.OneSignalClient;
import com.ferdi.youcontribute.models.Issue;
import com.ferdi.youcontribute.models.IssueChallenge;
import com.ferdi.youcontribute.models.IssueChallengeStatus;
import com.ferdi.youcontribute.models.Repository;
import com.ferdi.youcontribute.service.GithubClient;
import com.ferdi.youcontribute.service.IssueChallengeService;
import com.ferdi.youcontribute.service.IssueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
@AllArgsConstructor
public class TrackChallengesScheduler {
//IssueChallenge lardaki kapanmış issueları bulacak.

    private  final IssueService issueService;

    private  final IssueChallengeService issueChallengeService;

    private final OneSignalClient oneSignalClient;

    private  final GithubClient githubClient;

    @Scheduled(fixedRateString  = "${application.challengetracking}")
    public void challengeIssueScheduler()
    {
        log.info("Track Challenge issue scheduler started");

        this.issueChallengeService.list()  //Db den  tüm issuChallnege datasını çekiyoruz.
                .stream()
                .filter(issueChallenge -> IssueChallengeStatus.ACCEPTED.equals(issueChallenge.getStatus()))//ACCEPT olanları filtreliyoruz.
                .forEach(issueChallenge -> {
                    Repository repository=issueChallenge.getIssue().getRepository(); //Her bir issueChallenge datasının respository bilgisini alıyoruz.
                    Arrays.stream(this.githubClient.listPullRequest(repository.getOrganization(),repository.getRepository())) //Github clientına giderek pull requestler çekilir.
                            .filter(pull-> "ffiruz".equals(pull.getUser().getLogin()) && pull.getBody().contains(String.format("Fixes #%d",issueChallenge.getIssue().getGithubIssueNumber())) && "closed".equals(pull.getState()))//Eğer çekilen pull request user bilgisi ve status u bu kontrolü sağlıyorsa;ve body ike bu issue nun çözüldüğünü belirten bir text kontrolü
                            .findFirst()
                            .ifPresent(pulls -> {
                                this.issueChallengeService.updateChallengeStatus(issueChallenge.getId(),IssueChallengeStatus.COMPLETED);  //Eğer issure çözüldüyse complete çek.
                            });
                });
        log.info("Track Challenge issue scheduler finished");




    }

}
