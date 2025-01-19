package com.sapo.store_management.dto.option;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class OptionRequest {

    @NotNull(message = "Option name cannot be empty")
    private String name;

    @NotNull(message = "Option values cannot be null")
    @Size(min = 1, message = "Option values must contain at least one value")
    private List<String> values;
}
