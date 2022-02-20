package com.ferdi.youcontribute.repository;

import com.ferdi.youcontribute.models.Issue;
import com.ferdi.youcontribute.models.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface IssueRepository  extends PagingAndSortingRepository<Issue,Integer> {

    public List<Issue> findAll();

    public List<Issue> findByRepository(Repository repository);


}
