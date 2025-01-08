package com.sapo.store_management.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


public class UserRequest {

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;

    @NotBlank(message = "Full name cannot be blank")
    @Size(min = 3, max = 50, message = "Full name must be between 3 and 50 characters")
    private String fullname;

    private String role;
    private int age;

    public @NotBlank(message = "Username cannot be blank") @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank(message = "Username cannot be blank") @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String username) {
        this.username = username;
    }

    public @NotBlank(message = "Password cannot be blank") @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password cannot be blank") @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters") String password) {
        this.password = password;
    }

    public @NotBlank(message = "Full name cannot be blank") @Size(min = 3, max = 50, message = "Full name must be between 3 and 50 characters") String getFullname() {
        return fullname;
    }

    public void setFullname(@NotBlank(message = "Full name cannot be blank") @Size(min = 3, max = 50, message = "Full name must be between 3 and 50 characters") String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
