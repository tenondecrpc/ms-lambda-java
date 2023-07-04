package com.luifrangm.lambda.services;

import com.google.inject.Inject;
import com.luifrangm.lambda.enums.CustomerErrorEnum;
import com.luifrangm.lambda.exceptions.CustomerException;
import com.luifrangm.lambda.models.CustomerDto;
import com.luifrangm.lambda.repository.CustomerRepository;
import com.luifrangm.lambda.repository.CustomerRepositoryImpl;
import io.vavr.control.Try;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerCreateServiceImpl implements CustomerCreateService {

    private static final Logger LOG =
        Logger.getLogger(CustomerRepositoryImpl.class.getName());
    private final CustomerRepository repository;

    @Inject
    public CustomerCreateServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerDto create(CustomerDto customer) {
        return
            Try.of(()->
                repository.createCustomer(customer))
                .onFailure(throwable ->
                    LOG.log(Level.SEVERE,"CustomerCreateService - error al crear el cliente {0}",
                        throwable.getMessage()))
                .getOrElseThrow(throwable->
                    new CustomerException(CustomerErrorEnum.CREATE_CUSTOMER_EXCEPTION));
    }
}
