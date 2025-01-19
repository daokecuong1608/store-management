package com.sapo.store_management.mapper;

import com.sapo.store_management.dto.UserDTO;

import com.sapo.store_management.model.User;

public class UserMapper {

    public UserDTO toDTO(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setFull_name(entity.getFullname());
        return dto;
    }

}
