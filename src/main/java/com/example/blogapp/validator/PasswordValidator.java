package com.example.blogapp.validator;

import com.example.blogapp.data.UserRegistration;
import com.example.blogapp.validator.annotation.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, UserRegistration> {
    @Override
    public boolean isValid(UserRegistration userRegistration, ConstraintValidatorContext constraintValidatorContext) {
        String password = userRegistration.getPassword();
        String repeatPassword = userRegistration.getRepeatPassword();
        return password != null && password.equals(repeatPassword);
    }
}
