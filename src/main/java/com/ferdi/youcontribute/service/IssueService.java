package com.ferdi.youcontribute.service;

import com.ferdi.youcontribute.models.Issue;
import com.ferdi.youcontribute.models.Repository;
import com.ferdi.youcontribute.repository.IssueRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class IssueService {


    private final IssueRepository issueRepository;

    private final RepositoryService repositoryService;

    @Transactional
    public void saveAll(List<Issue> issues) {

        issues.forEach(issue-> {
            if(this.issueRepository.findByGithubIssueId(issue.getGithubIssueId()).isPresent())  //githubId->uniq
            {
                this.issueRepository.save(issue);
            }
        });

    }

    public  List<Issue> list(Integer repositoryId)
    {
        Repository repository=this.repositoryService.findById(repositoryId);

       return this.issueRepository.findByRepository(repository);

    }

    public Issue findRandomIssue() {

        return this.issueRepository.findRandomIssue()
                .orElseThrow(()->new EntityNotFoundException("Entity not found"));


    }


}
