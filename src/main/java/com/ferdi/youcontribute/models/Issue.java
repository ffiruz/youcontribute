package com.ferdi.youcontribute.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(strategy = "native",name = "native")
    private Integer id;

    private Long githubIssueId;

    private Long githubIssueNumber;

    private String title;

    @Column(columnDefinition = "text")
    private  String body;

    private String url;

    @ManyToOne
    @JoinColumn //"repository_id" (default) alanını bu tabloya ekleyecek.
    @JsonManagedReference
    private  Repository repository;  //Birden fazla issue , bir repositorye depend edebilir.

    @OneToOne(mappedBy = "issue",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonBackReference
    private IssueChallenge issueChallenge;
}
