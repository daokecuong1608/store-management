package com.sapo.store_management.dto.storage;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StorageCreateRequest {

    @NotBlank(message = "Storage code is required.")
    @Size(max = 50, message = "Code must not exceed 50 characters.")
    private String code;

    @NotBlank(message = "Storage name is required.")
    @Size(max = 50, message = "Name must not exceed 50 characters.")
    private String name;

    @NotBlank(message = "Address is required.")
    @Size(max = 200, message = "Address must not exceed 200 characters.")
    private String address;

    @NotNull(message = "Cash is required.")
    private Long cash;

    @NotNull(message = "Balance is required.")
    private Long balance;
}
