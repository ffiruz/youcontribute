package com.ferdi.youcontribute.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)  //JSON verisinde hangi tür verilerin bulunacağını belirtmek için kullanılır
public class ErrorResponse {

    private String message;

}
