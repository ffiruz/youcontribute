package com.ferdi.youcontribute.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true) //parent classdaki fildlarıda çağırır.
@EqualsAndHashCode(callSuper=false)
public class DuplicatedRepositoryException extends  RuntimeException {

    public DuplicatedRepositoryException(String  organization,String repository) {
        super(String.format("Organization:  %s , Repository: %s already exist",organization,repository));

    }
}
