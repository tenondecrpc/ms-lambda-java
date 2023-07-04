package com.luifrangm.lambda.mappers;

import com.luifrangm.lambda.entity.CustomerEntity;
import com.luifrangm.lambda.models.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerMapper CUSTOMER_MAPPER_INSTANCE =
        Mappers.getMapper(CustomerMapper.class);

    CustomerEntity toEntity (CustomerDto dto);

    CustomerDto toDto(CustomerEntity entity);

    default List<CustomerDto> toListDto(List<CustomerEntity> entityList) {
        final List<CustomerDto> customerList = new ArrayList<>();
        entityList.forEach(entity->
            customerList.add(toDto(entity)));
        return customerList;
    }

    default List<CustomerEntity> toEntityList(List<CustomerDto> customerList) {
        final List<CustomerEntity> entityList = new ArrayList<>();
        customerList.forEach(customer->
            entityList.add(toEntity(customer)));
        return entityList;
    }

}
