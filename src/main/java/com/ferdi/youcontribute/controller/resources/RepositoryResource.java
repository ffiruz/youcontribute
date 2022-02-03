package com.ferdi.youcontribute.controller.resources;

import com.ferdi.youcontribute.models.Repository;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class RepositoryResource {

    private String name;
    private  String organization;

    public static RepositoryResource createFor(Repository repository) //Model to Dto
    {
        return RepositoryResource.builder()
                .name("")
                .organization("")
                .build();
    }

    public static List<RepositoryResource> createFor(List<Repository> repositoryList)  //Model to Dto for List
    {
        return repositoryList.stream().map(RepositoryResource::createFor).collect(Collectors.toList());
    }


}
