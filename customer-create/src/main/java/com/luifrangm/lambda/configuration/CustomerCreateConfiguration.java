package com.luifrangm.lambda.configuration;

import com.google.inject.AbstractModule;
import com.luifrangm.lambda.repository.CustomerRepository;
import com.luifrangm.lambda.repository.CustomerRepositoryImpl;

public class CustomerCreateConfiguration extends AbstractModule {

    @Override
    protected void configure() {
        bind(CustomerRepository.class).to(CustomerRepositoryImpl.class);
    }

}
