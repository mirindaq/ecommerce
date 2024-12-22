package com.ecommerce.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Data
public class ProductDTO extends AbstractDTO {
    private Long id;
    private Double discount;
    private String rating;
    private String name;
    private Double price;
    private Integer stock;
    private String description;
    private String brandName;
    private String categoryName;
    private List<AttributeDTO> attributeList;
    private List<String> images;

}
