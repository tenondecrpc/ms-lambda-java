package com.luifrangm.lambda.models;

import com.luifrangm.lambda.enums.CustomerErrorEnum;
import com.luifrangm.lambda.exceptions.CustomerException;
import io.vavr.control.Try;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

import static com.luifrangm.lambda.constants.CustomerConstants.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    @Positive (message = CUSTOMER_ID_WRONG_ERROR)
    private int customerId;

    @NotNull (message = VALUE_NULL_ERROR)
    @NotBlank (message = VALUE_BLANK_ERROR)
    @NotEmpty (message = VALUE_EMPTY_ERROR)
    private String customerName;

    @NotNull (message = VALUE_NULL_ERROR)
    @NotBlank (message = VALUE_BLANK_ERROR)
    @NotEmpty (message = VALUE_EMPTY_ERROR)
    private String customerLastName;

    @NotNull (message = VALUE_NULL_ERROR)
    @NotBlank (message = VALUE_BLANK_ERROR)
    @NotEmpty (message = VALUE_EMPTY_ERROR)
    @Email (message = INVALID_EMAIL_ERROR)
    private String customerEmail;

    public void validate() {

        final Validator validator =
            Try.of(()->
                Validation.buildDefaultValidatorFactory()
                    .getValidator())
                .getOrNull();

        final Set<ConstraintViolation<CustomerDto>> errorsList =
            validator.validate(this);

        if(errorsList.size() > 0) {
            throw  new CustomerException(
                CustomerErrorEnum.getCustomerError(
                    errorsList.stream().findAny().get().getMessage()));
            }
    }
}
