package com.ferdi.youcontribute.controller;

import com.ferdi.youcontribute.controller.requests.CreateRepositoryRequest;
import com.ferdi.youcontribute.controller.resources.IssueResource;
import com.ferdi.youcontribute.controller.resources.RepositoryResource;
import com.ferdi.youcontribute.models.Issue;
import com.ferdi.youcontribute.service.IssueService;
import com.ferdi.youcontribute.service.RepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService ;
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping
    public List<IssueResource> list(@RequestParam("repository_id") Integer repositoryId)  //http://localhost:8090/issue?repository_id=34
    {
        return  IssueResource.createFor(this.issueService.list(repositoryId));  //model to dto
    }




//repository_id->snake case  (kelimeleri okumak daha kolay(resourcedan))->@RequestParam ->url deki parametre("repository_id")
//repositoryId -> camel case





}

