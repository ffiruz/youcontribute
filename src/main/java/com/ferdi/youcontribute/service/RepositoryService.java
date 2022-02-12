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
public class RepositoryService {  //Eğer ben bu servis içerisinde ki bir transacitonal metdou çağırdığımda , scope buradan başlar.

    private final RepositoryRepository repositoryRepository;

    @Transactional
    //for save operation->Aynı kayda biren fazla kişi erişiyorsa , datanın belli strajiler ile kayıt edilirken,  kitlenmesi lazım.Çünkü Race Condition problemi ortaya çıkabilir.
    //Düzgün transacitonal yapılmazsa data kirliliğine sebep olabilir.
    public void create(String repository,String organization) {
        Repository r = Repository.builder().repository(repository).organization(organization).build();
        repositoryRepository.save(r);
    }

    public List<Repository> list() {
        return repositoryRepository.findAll();
    }
}
