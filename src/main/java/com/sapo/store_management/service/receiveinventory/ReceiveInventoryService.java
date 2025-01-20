package com.sapo.store_management.service.receiveinventory;

import com.sapo.store_management.dto.common.DateRange;
import com.sapo.store_management.dto.common.NumberRange;
import com.sapo.store_management.dto.receiveinventory.request.ReceiveInventoryRequest;
import com.sapo.store_management.dto.receiveinventory.request.ProductImportRequest;
import com.sapo.store_management.dto.receiveinventory.response.ReceiveInventoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ReceiveInventoryService {
    Page<ReceiveInventoryResponse> getReceiveInventories(
        Pageable pageable,
        String search,
        List<String> branch,
        DateRange dateRange,
        NumberRange range,
        List<String> status
    );
    
    ReceiveInventoryResponse getReceiveInventory(Integer id);
    
    ReceiveInventoryResponse createReceiveInventory(ReceiveInventoryRequest request);
    
    ReceiveInventoryResponse updateReceiveInventory(Integer id, ReceiveInventoryRequest request);
    
    void deleteReceiveInventories(List<Integer> ids);
    
    void importInventory(Integer id, List<ProductImportRequest> products);
} 