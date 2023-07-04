package com.luifrangm.lambda.controllers;

import com.google.inject.Guice;
import com.luifrangm.lambda.configuration.CustomerGetConfiguration;
import com.luifrangm.lambda.enums.CustomerErrorEnum;
import com.luifrangm.lambda.exceptions.CustomerException;
import com.luifrangm.lambda.models.CustomerDto;
import com.luifrangm.lambda.services.CustomerGetService;
import com.luifrangm.lambda.services.CustomerGetServiceIMpl;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerGetController {

    private static final Logger LOG =
        Logger.getLogger(CustomerGetController.class.getName());
    private static final String ID = "id";

    public CustomerDto getCustomer(final Map<String,String> input) {
        LOG.log(Level.INFO,"init CustomerGetController with {0}",input);

        final int customerId = validateInput(input)
            .orElseThrow(()-> new CustomerException(CustomerErrorEnum.CUSTOMER_ID_EXCEPTION));

        final CustomerGetService customerGetService =
            Guice.createInjector(new CustomerGetConfiguration())
                .getInstance(CustomerGetServiceIMpl.class);

        return
            customerGetService.getCustomer(customerId);
    }

    private Optional<Integer> validateInput(final Map<String,String> input) {
        return
            Objects.isNull(input.get(ID)) ||
                input.get(ID).trim().equals("") ||
                    Integer.parseInt(input.get(ID)) < 1
                        ? Optional.empty()
                            : Optional.of(Integer.parseInt(input.get("id")));
    }

}
