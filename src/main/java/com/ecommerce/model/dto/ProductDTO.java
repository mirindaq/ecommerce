package com.ecommerce.model.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
public class ProductDTO extends AbstractDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer stock;
    private Double discount;
    private String description;
    private List<String> images;
    private String brandName;
    private String categoryName;
    private String rating;
    private List<Map<String,String>> attributeList;

}
