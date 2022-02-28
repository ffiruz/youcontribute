package com.ferdi.youcontribute.repository;

import com.ferdi.youcontribute.models.Issue;
import com.ferdi.youcontribute.models.IssueChallenge;
import com.ferdi.youcontribute.models.IssueChallengeStatus;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface IssueChallengeRepository  extends PagingAndSortingRepository<IssueChallenge,Integer> {

    Optional<IssueChallenge> findByStatusIn(List<IssueChallengeStatus> ongoingStatuses);

    List<IssueChallenge> findAll();


}
