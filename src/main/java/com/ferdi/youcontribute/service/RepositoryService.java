package com.ferdi.youcontribute.service;

import com.ferdi.youcontribute.controller.resources.RepositoryResource;
import com.ferdi.youcontribute.models.Repository;
import com.ferdi.youcontribute.repository.RepositoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepositoryService {

    private final RepositoryRepository repositoryRepository;

    @Transactional  //for save operation
    public void create(String reposiroy,String organization) {
        Repository r = Repository.builder().repository(reposiroy).organization(organization).build();
        repositoryRepository.save(r);
    }

    public List<Repository> list() {
        return repositoryRepository.findAll();
    }
}
