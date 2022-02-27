package com.ferdi.youcontribute.controller.resources;

import com.ferdi.youcontribute.models.Issue;
import com.ferdi.youcontribute.models.IssueChallenge;
import com.ferdi.youcontribute.models.IssueChallengeStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class IssueChallengeResource {
    private Integer id;

    private Integer issueId;

    private Integer repositoryId;

    private String repositoryTitle;

    private String issueTitle;

    private Date creationDate;

    private IssueChallengeStatus status;

    public static IssueChallengeResource createFor(IssueChallenge issueChallenge) //Model to Dto
    {
        Issue issue = issueChallenge.getIssue();
        return IssueChallengeResource.builder()
                .id(issueChallenge.getId())
                .issueId(issue.getId())
                .repositoryId(issue.getRepository().getId())
                .repositoryTitle(issue.getRepository().getRepository())
                .issueTitle(issue.getTitle())
                .creationDate(issueChallenge.getCreatedAt())
                .status(issueChallenge.getStatus())
                .build();
    }

    public static List<IssueChallengeResource> createFor(List<IssueChallenge> issueChallengeList)  //Model to Dto for List
    {
        return issueChallengeList.stream().map(IssueChallengeResource::createFor).collect(Collectors.toList());
    }

}
