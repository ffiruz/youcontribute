package com.ferdi.youcontribute.service;

import com.ferdi.youcontribute.controller.resources.RepositoryResource;
import com.ferdi.youcontribute.exception.DuplicatedRepositoryException;
import com.ferdi.youcontribute.models.Repository;
import com.ferdi.youcontribute.repository.RepositoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RepositoryService {  //Eğer ben bu servis içerisinde ki bir transacitonal metdou çağırdığımda , scope buradan başlar.

    private final RepositoryRepository repositoryRepository;

    @Transactional
    //for save operation->Aynı kayda biren fazla kişi erişiyorsa , datanın belli strajiler ile kayıt edilirken,  kitlenmesi lazım.Çünkü Race Condition problemi ortaya çıkabilir.
    //Düzgün transacitonal yapılmazsa data kirliliğine sebep olabilir.
    public void create(String repository, String organization) {
        this.validate(organization, repository);  //validation control
        Repository r = Repository.builder().repository(repository).organization(organization).build();
        repositoryRepository.save(r);
    }

    public List<Repository> list() {
        return repositoryRepository.findAll();
    }

    private void validate(String organization, String repository) {
        this.repositoryRepository.findByOrganizationAndRepository(organization, repository)
                .ifPresent(item -> {
                    throw new DuplicatedRepositoryException(organization, repository);  //duplicate exception -> Eğer organization ,repository varsa bir exception fırlatacak.
                });
    }

    public Repository findById(Integer repositoryId) {
        return this.repositoryRepository.findById(repositoryId).orElseThrow(() ->  //bir data bulamazsa bu exceptionu fırlatacak.Handle 404
                new EntityNotFoundException(String.format("Repository id:%d is not found", repositoryId)));
    }
}
