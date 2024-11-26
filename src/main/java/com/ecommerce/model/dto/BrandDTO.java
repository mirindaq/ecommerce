package com.ecommerce.model.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
public class BrandDTO {
    private Long id;
    private String name;
    private String description;
    private List<Long> categoriesId;

}