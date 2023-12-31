package com.microService.microService.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestUser {
    @NotNull(message="Email cannot be null")
    @Size(min=2,message = " Email not be less than two characters")
    @Email
    private String email;
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message="name not be less than two characters")
    private String name;

    private String pwd;

}
