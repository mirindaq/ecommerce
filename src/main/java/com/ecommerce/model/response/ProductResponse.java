package com.ecommerce.model.response;

import com.ecommerce.model.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private List<ProductDTO> products;
    private Long total;
    private Integer page;
    private Integer limit;

}
