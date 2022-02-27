package com.ferdi.youcontribute.schedulers;


import com.ferdi.youcontribute.client.OneSignalClient;
import com.ferdi.youcontribute.models.Issue;
import com.ferdi.youcontribute.models.IssueChallenge;
import com.ferdi.youcontribute.models.Repository;
import com.ferdi.youcontribute.service.IssueChallengeService;
import com.ferdi.youcontribute.service.IssueService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class ChallengeIssueSchedulers {

    private  final IssueService issueService;

    private  final IssueChallengeService issueChallengeService;

    private final OneSignalClient oneSignalClient;


    @Scheduled(fixedRateString  = "${application.challengefrequency}")
    public void challengeIssueScheduler()
    {
        log.info("Challenge issue scheduler started");
        if(this.issueChallengeService.hasOnGoingChallenge())
        {
            log.warn("This is already ongoing challenge, skipping...");
            return ;
        }
        Issue randomIssue=this.issueService.findRandomIssue();
        log.info("Found a random Issue",randomIssue.getId());
        IssueChallenge issueChallenge=issueChallengeService.create(randomIssue);
        oneSignalClient.sendNotification(issueChallenge.getId(),randomIssue.getTitle());
        log.info("Challenge issue scheduler finished");




    }


}
