package com.luifrangm.lambda.repository;

import com.luifrangm.lambda.models.CustomerDto;

import java.util.Optional;

public interface CustomerRepository {
    CustomerDto createCustomer(final CustomerDto customer);
    Optional<CustomerDto> getCustomer(final int customerId);
}
