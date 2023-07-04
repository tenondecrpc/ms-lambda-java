package com.luifrangm.lambda.exceptions;

import com.luifrangm.lambda.enums.CustomerErrorEnum;
import lombok.Getter;

@Getter
public class CustomerException extends RuntimeException {

    public CustomerException(CustomerErrorEnum response) {

        super(response.toString(),null,true,false);
    }

}
