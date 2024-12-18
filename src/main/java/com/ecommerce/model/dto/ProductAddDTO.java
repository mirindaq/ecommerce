package com.ecommerce.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductAddDTO {
    private Long id;
    private Double discount;
    private String rating;
    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @NotNull(message = "Giá sản phẩm không được để trống")
    private Double price;

    @NotNull(message = "Số lượng không được để trống")
    private Integer stock;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotBlank(message = "Thương hiệu không được để trống")
    private String brandName;

    @NotBlank(message = "Danh mục không được để trống")
    private String categoryName;

    private List<AttributeDTO> attributes;

    private List<MultipartFile> images;

}
