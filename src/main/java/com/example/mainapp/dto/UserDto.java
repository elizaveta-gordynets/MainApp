package com.example.mainapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class UserDto {

    @NotNull
    private String username;
    @NotNull
    @Size(min = 11, max = 50)
    @Pattern(regexp = "[0-9]+[a-zA-Z]+[@!#$%^&*,.?~]+$", message = "Password should contain at least 1 number," +
            "and at least 1 special sign and be at least 11 symbols long")
    private String password;
    @NotNull
    private String name;
    @NotNull
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@" +
            "[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$")
    private String email;
}
