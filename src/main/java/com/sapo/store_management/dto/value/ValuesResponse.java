package com.sapo.store_management.dto.value;

import com.sapo.store_management.model.Option;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ValuesResponse {
        private Integer id;
        private String name;
        private LocalDateTime created_at;
        private LocalDateTime updated_at;
    }
