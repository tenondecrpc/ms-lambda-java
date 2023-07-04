package com.luifrangm.lambda.repository;

import com.luifrangm.lambda.entity.CustomerEntity;
import com.luifrangm.lambda.enums.CustomerErrorEnum;
import com.luifrangm.lambda.exceptions.CustomerException;
import com.luifrangm.lambda.mappers.CustomerMapper;
import com.luifrangm.lambda.models.CustomerDto;
import com.luifrangm.lambda.utils.DynamoDbUtils;
import io.vavr.control.Try;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerRepositoryImpl implements CustomerRepository {

    private static final String TABLE_NAME = "CustomerTable";

    private static final DynamoDbEnhancedClient dynamoDb =
        DynamoDbUtils.getDynamoDbEnhancedClient();

    private static final DynamoDbTable<CustomerEntity> customerTable =
        dynamoDb.table(TABLE_NAME, TableSchema.fromBean(CustomerEntity.class));

    private final CustomerMapper customerMapper =
        CustomerMapper.CUSTOMER_MAPPER_INSTANCE;

    private static final Logger LOG =
        Logger.getLogger(CustomerRepositoryImpl.class.getName());

    @Override
    public CustomerDto createCustomer(CustomerDto customer) {

        LOG.log(Level.INFO,"Adicionando {0}, a la tabla {1}",
            new Object[] {customer,TABLE_NAME});

        return
            Try.of(()-> {
                customerTable.putItem(customerMapper.toEntity(customer));
                return customer;
            })
                .onSuccess(res->
                    LOG.log(Level.INFO,"cliente creado con exito {0}",res))
                .onFailure(throwable ->
                    LOG.log(Level.SEVERE,"Error al crear el cliente {0}",
                        throwable.getMessage()))
                .getOrElseThrow(err->
                    new CustomerException(CustomerErrorEnum.UNEXPECTED_EXCEPTION));
    }

    @Override
    public Optional<CustomerDto> getCustomer(int customerId) {
        final CustomerDto customer =
            Try.of(()->
                customerMapper.toDto(
                    customerTable.getItem(buildKey(customerId))))
            .onFailure(throwable ->
                LOG.log(Level.SEVERE,"Error al buscar customer {0}",throwable.getMessage()))
            .getOrElseThrow(()->
                new CustomerException(CustomerErrorEnum.UNEXPECTED_EXCEPTION));

        return
            Objects.isNull(customer)
                ? Optional.empty()
                    : Optional.of(customer);
    }

    private Key buildKey(final int customerId) {
        return
            Key.builder()
                .partitionValue(customerId)
                .build();
    }
}
