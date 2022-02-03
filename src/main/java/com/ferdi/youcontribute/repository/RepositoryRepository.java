package com.ferdi.youcontribute.repository;

import com.ferdi.youcontribute.models.Repository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RepositoryRepository  extends PagingAndSortingRepository<Repository,Integer> {

}
