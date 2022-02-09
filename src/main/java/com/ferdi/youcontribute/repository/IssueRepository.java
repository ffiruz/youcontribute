package com.ferdi.youcontribute.repository;

import com.ferdi.youcontribute.models.Issue;
import com.ferdi.youcontribute.models.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface IssueRepository  extends PagingAndSortingRepository<Issue,Integer> {

    public List<Issue> findAll();
}
