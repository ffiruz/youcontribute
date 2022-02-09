package com.ferdi.youcontribute.controller.requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateRepositoryRequest {

    private String organization;
    private  String repository;

}
