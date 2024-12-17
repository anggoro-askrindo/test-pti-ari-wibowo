package com.example.assesmentbackend.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Repeatable(TransactionConditionals.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {TransactionConditionalValidator.class})
public @interface TransactionConditional {
    String message() default "This field is required.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String selected();
    String[] required();
    String[] values();
}
