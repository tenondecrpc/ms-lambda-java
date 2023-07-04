package com.luifrangm.lambda.services;

import com.luifrangm.lambda.models.CustomerDto;

public interface CustomerGetService {
    CustomerDto getCustomer(int id);
}
