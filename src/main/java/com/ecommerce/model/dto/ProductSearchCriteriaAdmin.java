package com.ecommerce.model.dto;

import lombok.Data;

@Data
public class ProductSearchCriteriaAdmin {
    private String name;
    private Double minPrice;
    private Double maxPrice;
    private Double discount;
    private String brandName;
    private String categoryName;
    private Integer page;
    private Integer limit;
    private String sortBy;
    private String sortOrder;
    private Boolean active;
    private String createdBy;
    private String createdDateFrom;
    private String createdDateTo;
    private String modifiedBy;
}
