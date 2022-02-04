package com.ferdi.youcontribute.controller.requests;

import lombok.Data;

@Data
public class CreateRepositoryRequest {

    private String organization;
    private  String repository;

}
