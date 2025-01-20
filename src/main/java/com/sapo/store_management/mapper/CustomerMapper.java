package com.sapo.store_management.mapper;

import com.sapo.store_management.dto.customer.CustomerDTO;
import com.sapo.store_management.dto.customer.OrderResponse;
import com.sapo.store_management.model.Customer;
import com.sapo.store_management.model.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setFullname(dto.getFullname());
        if (dto.getBirthday() != null) {
            customer.setBirthday(Date.valueOf(dto.getBirthday()));
        }
        customer.setPhone(dto.getPhone());
        customer.setGender(dto.getGender());
        customer.setDistrictCode(dto.getDistrictCode());
        customer.setWardCode(dto.getWardCode());
        customer.setAddressDetail(dto.getAddressDetail());
        customer.setNote(dto.getNote());
        return customer;
    }

    public CustomerDTO toDTO(Customer entity) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(entity.getId());
        dto.setFullname(entity.getFullname());
        if (entity.getBirthday() != null) {
            dto.setBirthday(entity.getBirthday().toLocalDate());
        }
        dto.setPhone(entity.getPhone());
        dto.setGender(entity.getGender());
        dto.setDistrictCode(entity.getDistrictCode());
        dto.setWardCode(entity.getWardCode());
        dto.setAddressDetail(entity.getAddressDetail());
        dto.setNote(entity.getNote());

        if (entity.getOrders() != null) {
            dto.setOrders(entity.getOrders().stream()
                    .map(this::toOrderResponse)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    private OrderResponse toOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        return response;
    }
}
