package com.sapo.store_management.dto.tag;


import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TagResponse {
    private Integer id;
    private String name;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
