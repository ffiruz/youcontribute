package com.ferdi.youcontribute.controller;

import com.ferdi.youcontribute.controller.requests.UpdateChallengeStatusRequest;
import com.ferdi.youcontribute.controller.resources.IssueChallengeResource;
import com.ferdi.youcontribute.service.IssueChallengeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
public class IssueChallengeController {

    private final IssueChallengeService issueChallengeService ;
    public IssueChallengeController(IssueChallengeService issueChallengeService) {
        this.issueChallengeService = issueChallengeService;
    }

    @PatchMapping("/{id}")
    public void updateChallengeStatus(@PathVariable("id") Integer issueChallengeId, @RequestBody UpdateChallengeStatusRequest updateChallengeStatusRequest)  //http://localhost:8090/issue?repository_id=34
    {
        this.issueChallengeService.updateChallengeStatus(issueChallengeId,updateChallengeStatusRequest.getStatus());
    }

    @GetMapping
    public List<IssueChallengeResource> list()
    {
       return IssueChallengeResource.createFor(this.issueChallengeService.list());
    }



}
