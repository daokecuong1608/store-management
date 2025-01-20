package com.sapo.store_management.mapper;

import com.sapo.store_management.dto.common.SupplierInfo;
import com.sapo.store_management.dto.common.UserInfo;
import com.sapo.store_management.dto.receiveinventory.request.ReceiveInventoryRequest;
import com.sapo.store_management.dto.receiveinventory.response.ReceiveInventoryResponse;
import com.sapo.store_management.model.ReceiveInventory;
import com.sapo.store_management.model.Supplier;
import com.sapo.store_management.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReceiveInventoryMapper {
    private final ModelMapper mapper;

    public ReceiveInventory toEntity(ReceiveInventoryRequest request) {
        return mapper.map(request, ReceiveInventory.class);
    }

    public ReceiveInventoryResponse toResponse(ReceiveInventory entity) {
        return mapper.map(entity, ReceiveInventoryResponse.class);
    }

    public SupplierInfo mapSupplier(Supplier supplier) {
        return mapper.map(supplier, SupplierInfo.class);
    }

    public UserInfo mapUser(User user) {
        return mapper.map(user, UserInfo.class);
    }
} 