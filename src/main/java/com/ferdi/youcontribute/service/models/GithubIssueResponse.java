package com.ferdi.youcontribute.service.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
//Githubdan under score şeklinde response dönüyor.(repository_id örnek.)->Aşağıdaki SnakeCaseStrtegey belittiğimiz alanlara map edeceğini bilir.(camel case)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) //Aşağıda yazdığımız camel case formatta ki değişkenleri(örnek:htmlUrl),snakeCase(örnek:html_url) e çevirecek.Deserilaze işleminde.
@Data
public class GithubIssueResponse {

    public String url;
    public String repositoryUrl;
    public String labelsUrl;
    public String commentsUrl;
    public String eventsUrl;
    public String htmlUrl;
    public Long id;
    public String nodeId;
    public long number;
    public String title;
    public User user;
    public ArrayList<Label> labels;
    public String state;
    public boolean locked;
    public Object assignee;
    public ArrayList<Assignee> assignees;
    public Object milestone;
    public int comments;
    public Date created_at;
    public Date updated_at;
    public Object closed_at;
    public String authorAssociation;
    public Object activeLockReason;
    public boolean draft;
   public PullRequest pullRequest;
    public String body;
   public Reactions reactions;
    public String timelineUrl;
    public Object performedViaGithubApp;

    public static  class User{
        public String login;
        public int id;
        public String nodeId;
        public String avatarUrl;
        public String gravatarId;
        public String url;
        public String htmlUrl;
        public String followersUrl;
        public String followingUrl;
        public String gistsUrl;
        public String starredUrl;
        public String subscriptionsUrl;
        public String organizationsUrl;
        public String reposUrl;
        public String eventsUrl;
        public String receivedEventsUrl;
        public String type;
        public boolean siteAdmin;
    }

    public static  class  Assignee
    {
        public String login;
        public int id;
        public String nodeId;
        public String avatarUrl;
        public String gravatarId;
        public String url;
        public String htmlUrl;
        public String followersUrl;
        public String followingUrl;
        public String gistsUrl;
        public String starredUrl;
        public String subscriptionsUrl;
        public String organizationsUrl;
        public String reposUrl;
        public String eventsUrl;
        public String receivedEventsUrl;
        public String type;
        public boolean siteAdmin;
    }
    public  static  class Label {
        public long id;
        public String nodeId;
        public String url;
        public String name;
        public String color;
        @JsonProperty("default")
        public boolean myDefault;
        public String description;
    }

    public static class PullRequest{
        public String url;
        public String html_url;
        public String diff_url;
        public String patch_url;
        public Object merged_at;
    }


    public class Reactions{
        public String url;
        public int total_count;
        @JsonProperty("+1")
        public int __1;
        @JsonProperty("-1")
        public int _1;
        public int laugh;
        public int hooray;
        public int confused;
        public int heart;
        public int rocket;
        public int eyes;
    }
}


