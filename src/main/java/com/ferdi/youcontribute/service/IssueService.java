package com.ferdi.youcontribute.service;

import com.ferdi.youcontribute.models.Issue;
import com.ferdi.youcontribute.repository.IssueRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class IssueService {


    private final IssueRepository issueRepository;

    @Transactional
    public void saveAll(List<Issue> issues) {
        issueRepository.saveAll(issues);
    }
}
