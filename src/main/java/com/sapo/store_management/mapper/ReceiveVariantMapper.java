package com.sapo.store_management.mapper;

import com.sapo.store_management.dto.receiveinventory.request.ReceiveVariantRequest;
import com.sapo.store_management.dto.receiveinventory.response.ReceiveVariantResponse;
import com.sapo.store_management.model.ReceiveVariant;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReceiveVariantMapper {
    private final ModelMapper mapper;

    public ReceiveVariant toEntity(ReceiveVariantRequest request) {
        return mapper.map(request, ReceiveVariant.class);
    }

    public ReceiveVariantResponse toResponse(ReceiveVariant entity) {
        return mapper.map(entity, ReceiveVariantResponse.class);
    }
} 