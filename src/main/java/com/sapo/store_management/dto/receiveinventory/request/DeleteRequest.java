package com.sapo.store_management.dto.receiveinventory.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Data
public class DeleteRequest {
    @NotEmpty(message = "IDs list cannot be empty")
    private List<Integer> ids;
} 