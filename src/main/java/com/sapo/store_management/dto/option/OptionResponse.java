package com.sapo.store_management.dto.option;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptionResponse {
    private Integer id;
    private String name;
    private List<String> values;
}