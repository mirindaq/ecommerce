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
    private String image;
//    private Long brandId;
    private String brandName;
//    private Long categoryId;
    private String categoryName;
    private List<Map<String,String>> attributeList;

}
