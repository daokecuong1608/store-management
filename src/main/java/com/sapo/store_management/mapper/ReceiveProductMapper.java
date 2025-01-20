package com.sapo.store_management.mapper;

import com.sapo.store_management.dto.receiveinventory.request.ReceiveProductRequest;
import com.sapo.store_management.dto.receiveinventory.response.ReceiveProductResponse;
import com.sapo.store_management.model.ReceiveProduct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReceiveProductMapper {
    private final ModelMapper mapper;

    public ReceiveProduct toEntity(ReceiveProductRequest request) {
        return mapper.map(request, ReceiveProduct.class);
    }

    public ReceiveProductResponse toResponse(ReceiveProduct entity) {
        return mapper.map(entity, ReceiveProductResponse.class);
    }
} 