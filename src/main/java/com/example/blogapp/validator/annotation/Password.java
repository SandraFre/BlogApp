package com.example.blogapp.validator.annotation;

import com.example.blogapp.validator.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

    String message() default "{password.constraints.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
