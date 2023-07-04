package com.luifrangm.lambda.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class CustomerEntity {

    private int customerId;
    private String customerName;
    private String customerLastName;
    private String customerEmail;


    @DynamoDbPartitionKey()
    public int getCustomerId() {return customerId;}
    public void setCustomerId(int customerId) {this.customerId = customerId;}

    public String getCustomerName() {return customerName;}
    public void setCustomerName(String customerName) {this.customerName = customerName;}

    public String getCustomerLastName() {return customerLastName;}
    public void setCustomerLastName(String customerLastName) {this.customerLastName = customerLastName;}

    public String getCustomerEmail() {return customerEmail;}
    public void setCustomerEmail(String customerEmail) {this.customerEmail = customerEmail;}
}
