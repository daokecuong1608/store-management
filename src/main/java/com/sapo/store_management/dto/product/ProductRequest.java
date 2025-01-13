package com.sapo.store_management.dto.product;

import com.sapo.store_management.dto.option.OptionRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ProductRequest {
    @NotNull(message = "Code cannot be blank")
    @Size(max = 50, message = "Code cannot exceed 50 characters")
    private String code;
    @NotNull(message = "Name cannot be blank")
    @Size(max = 255, message = "Name cannot exceed 255 characters")
    private String name;
    @NotNull(message = "Description cannot be blank")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private int price;
    @Min(value = 0, message = "Capital price must be greater than or equal to 0")
    private int capital_price;
    private String image;
    private boolean status;
    private Integer brand;
    private List<Integer> categories;
    private List<Integer> tags;
    private List<OptionRequest> options;
}
