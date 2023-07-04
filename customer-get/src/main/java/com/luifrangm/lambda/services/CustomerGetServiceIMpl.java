package com.luifrangm.lambda.services;

import com.google.inject.Inject;
import com.luifrangm.lambda.enums.CustomerErrorEnum;
import com.luifrangm.lambda.exceptions.CustomerException;
import com.luifrangm.lambda.models.CustomerDto;
import com.luifrangm.lambda.repository.CustomerRepository;
import com.luifrangm.lambda.repository.CustomerRepositoryImpl;
import io.vavr.control.Try;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerGetServiceIMpl implements CustomerGetService {

    private static final Logger LOG =
        Logger.getLogger(CustomerRepositoryImpl.class.getName());

    private final CustomerRepository repository;

    @Inject
    public CustomerGetServiceIMpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerDto getCustomer(int id) {
        final Optional<CustomerDto> customer =
            Try.of(()->
                repository.getCustomer(id))
                .onFailure(throwable ->
                    LOG.log(Level.SEVERE,"Error al consultar el cliente {0}",
                        throwable.getMessage()))
                .getOrElseThrow(err->
                    new CustomerException(CustomerErrorEnum.UNEXPECTED_EXCEPTION));

        return
            customer.orElseThrow(
                ()-> new CustomerException(CustomerErrorEnum.CUSTOMER_NOT_EXIST_EXCEPTION));

    }
}
