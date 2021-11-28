package com.example.blogapp.validator;

import com.example.blogapp.data.UserRegistration;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserRegistrationValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegistration.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username", "userRegistrationValidator.username");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "userRegistrationValidator.email");
    }
}
