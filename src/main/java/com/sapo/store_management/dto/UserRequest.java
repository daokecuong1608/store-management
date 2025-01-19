package com.sapo.store_management.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {

    @NotNull(message = "Username cannot be blank")
    private String username;

    @NotNull(message = "Password cannot be blank")
    private String password;

    @NotNull(message = "Full name cannot be blank")
    private String fullname;

    @NotNull(message = "role  cannot be blank")
    private String role;

    @NotNull(message = "age  cannot be blank")
    private int age;

}
