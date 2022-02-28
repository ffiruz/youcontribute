package com.ferdi.youcontribute.controller.resources;

import com.ferdi.youcontribute.models.Issue;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class IssueResource {

    private int id;

    private Long githubId;

    private Long githubNumber;

    private String title;

    private String body;

    private  String url;

    public static IssueResource createFor(Issue issue)
    {
        return IssueResource.builder()
                .id(issue.getId())
                .githubId(issue.getGithubIssueId())
                .title(issue.getTitle())
                .body(issue.getBody())
                .url(issue.getUrl())
                .githubNumber(issue.getGithubIssueNumber())
                .build();
    }

    public static List<IssueResource> createFor(List<Issue> listIssue)
    {
        return  listIssue.stream().map(item -> createFor(item)).collect(Collectors.toList());
    }



}

