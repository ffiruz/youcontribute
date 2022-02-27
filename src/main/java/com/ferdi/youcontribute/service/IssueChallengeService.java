package com.ferdi.youcontribute.service;

import com.ferdi.youcontribute.models.Issue;
import com.ferdi.youcontribute.models.IssueChallenge;
import com.ferdi.youcontribute.models.IssueChallengeStatus;
import com.ferdi.youcontribute.repository.IssueChallengeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class IssueChallengeService {

    private final IssueChallengeRepository issueChallengeRepository;

    public boolean hasOnGoingChallenge() {

        return this.issueChallengeRepository.findByStatusIn(IssueChallengeStatus.ongoingStatuses()).isPresent();

    }

    @Transactional
    public IssueChallenge create(Issue issue) {

        IssueChallenge issueChallenge = IssueChallenge.builder().issue(issue).status(IssueChallengeStatus.PENDING).build();
        return this.issueChallengeRepository.save(issueChallenge);
    }

    public List<IssueChallenge> list() {
        return this.issueChallengeRepository.findAll();
    }


    public void updateChallengeStatus(Integer issueChallengeId, IssueChallengeStatus issueChallengeStatus) {

        IssueChallenge issueChallenge = this.issueChallengeRepository.findById(issueChallengeId).orElseThrow(() -> new EntityNotFoundException("Issue challenge " + issueChallengeId + " not found"));
        issueChallenge.setStatus(issueChallengeStatus);
        this.issueChallengeRepository.save(issueChallenge);

    }

}
