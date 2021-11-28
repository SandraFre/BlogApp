package com.example.blogapp.data;

import com.example.blogapp.validator.annotation.Password;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Password
public class UserRegistration {
    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Min(8)
    private String password;

    @NotBlank
    @Min(8)
    private String repeatPassword;

}
