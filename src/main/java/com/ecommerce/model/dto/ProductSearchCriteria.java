package com.ecommerce.model.dto;

import lombok.Data;

@Data
public class ProductSearchCriteria {
    private String name;
    private Double minPrice;
    private Double maxPrice;
    private Double discount;
    private String brandName;
    private String categoryName;
}
