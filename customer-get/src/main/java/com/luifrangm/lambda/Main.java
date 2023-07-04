package com.luifrangm.lambda;

import com.google.inject.Guice;
import com.luifrangm.lambda.configuration.CustomerGetConfiguration;
import com.luifrangm.lambda.controllers.CustomerGetController;
import com.luifrangm.lambda.models.CustomerDto;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        final CustomerGetController controller =
            Guice.createInjector(new CustomerGetConfiguration())
                .getInstance(CustomerGetController.class);

        final Map<String,String> input = new HashMap<>();

        input.put("id","25");

        final CustomerDto customer = controller.getCustomer(input);

        System.out.println( customer);
    }
}