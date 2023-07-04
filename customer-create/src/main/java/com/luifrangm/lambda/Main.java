package com.luifrangm.lambda;

import com.luifrangm.lambda.controllers.CustomerCreateController;
import com.luifrangm.lambda.models.CustomerDto;

public class Main {
    public static void main(String[] args) {

        CustomerCreateController controller = new CustomerCreateController();

        final CustomerDto customer = new CustomerDto();
        customer.setCustomerId(30);
        customer.setCustomerName("LUIS");
        customer.setCustomerLastName("GUTIERREZ");
        customer.setCustomerEmail("micorreo@correo.com");

        final Object response =
            controller.createCustomer(customer);

        System.out.println(response);

    }
}