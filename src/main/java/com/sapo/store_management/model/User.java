package com.sapo.store_management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @NotBlank(message = "fullname cannot be blank")
    @Size(min = 3, max = 50, message = "fullname must be between 3 and 50 characters")
    @Column(name = "full_name", nullable = false, unique = true)
    private String fullname;

    @Size(min = 1, max = 100, message = "age must be between 1 and 100 ")
    @Column(name = "age")
    private int age;


    public @NotBlank(message = "fullname cannot be blank") @Size(min = 3, max = 50, message = "fullname must be between 3 and 50 characters") String getFullname() {
        return fullname;
    }

    public void setFullname(@NotBlank(message = "fullname cannot be blank") @Size(min = 3, max = 50, message = "fullname must be between 3 and 50 characters") String fullname) {
        this.fullname = fullname;
    }

    @Size(min = 1, max = 100, message = "age must be between 1 and 100 ")
    public int getAge() {
        return age;
    }

    public void setAge(@Size(min = 1, max = 100, message = "age must be between 1 and 100 ") int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
