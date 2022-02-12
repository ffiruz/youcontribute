package com.ferdi.youcontribute.schedulers;

import com.ferdi.youcontribute.manager.RepositoryManager;
import com.ferdi.youcontribute.models.Repository;
import com.ferdi.youcontribute.service.RepositoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class ImportIssueScheduler {

    private  final RepositoryManager repositoryManager;

    private final RepositoryService repositoryService;

    @Scheduled(fixedRateString  = "${application.importfrequency}",initialDelay = 6000)//initialDelay ->uygulama ilk ayağa kalktığından , ne kadar süre sonra çalışcağını verebiliriz.(60 saniye)
  public void importIssueScheduler()
    {
        //db den tüm repositoryi çekeceğiz.Ardından importIssue metodu ile db ye kaydedeceğiz.
        log.info("Import scheduler started");
        List<Repository> listRepository=repositoryService.list();
        listRepository.forEach(repositoryManager::importIssues);

        log.info("Import scheduler finished");


    }
}
