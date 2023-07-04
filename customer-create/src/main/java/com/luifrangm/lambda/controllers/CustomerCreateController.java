package com.luifrangm.lambda.controllers;

import com.google.inject.Guice;
import com.luifrangm.lambda.configuration.CustomerCreateConfiguration;
import com.luifrangm.lambda.models.CustomerDto;
import com.luifrangm.lambda.services.CustomerCreateServiceImpl;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerCreateController {

    private static final Logger LOG =
        Logger.getLogger(CustomerCreateController.class.getName());

    public CustomerDto createCustomer(CustomerDto customer) {
        LOG.log(Level.INFO,"init CustomerCreateController with {0}",customer);

        customer.validate();

        final CustomerCreateServiceImpl customerService =
            Guice.createInjector(new CustomerCreateConfiguration())
                .getInstance(CustomerCreateServiceImpl.class);

        return
            customerService.create(customer);
    }

}
