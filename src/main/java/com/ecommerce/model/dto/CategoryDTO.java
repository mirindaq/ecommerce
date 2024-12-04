package com.ecommerce.model.dto;

import lombok.*;

import java.util.List;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private String image;
    private List<String> listAttribute;
}
