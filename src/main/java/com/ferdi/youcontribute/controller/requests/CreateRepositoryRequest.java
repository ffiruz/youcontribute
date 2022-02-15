package com.ferdi.youcontribute.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRepositoryRequest {

    private String organization;
    private  String repository;

}
