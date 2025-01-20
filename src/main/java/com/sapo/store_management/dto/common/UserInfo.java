package com.sapo.store_management.dto.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {
    private Integer id;
    private String username;
    private String fullName;
    private String email;
} 