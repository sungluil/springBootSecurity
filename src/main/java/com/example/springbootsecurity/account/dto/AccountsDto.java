package com.example.springbootsecurity.account.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class AccountsDto {
    @NotBlank
    @Length(min = 3, max = 20)
    private String username;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{3,12}$")
    private String password;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String age;

}
