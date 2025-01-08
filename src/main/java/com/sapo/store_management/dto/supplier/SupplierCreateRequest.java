package com.sapo.store_management.dto.supplier;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SupplierCreateRequest {

    @NotBlank(message = "Supplier code is required.")
    @Size(max = 50, message = "Code must not exceed 50 characters.")
    private String code;

    @NotBlank(message = "Supplier name is required.")
    @Size(max = 50, message = "Name must not exceed 50 characters.")
    private String name;

    @Size(max = 200, message = "Address must not exceed 200 characters.")
    private String address;

    @NotBlank(message = "Phone number is required.")
    @Size(max = 20, message = "Phone number must not exceed 20 characters.")
    private String phone;

    @Email(message = "Email must be valid.")
    @Size(max = 50, message = "Email must not exceed 50 characters.")
    private String email;
}