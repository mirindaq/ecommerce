package com.ecommerce.api.admin;


import com.ecommerce.model.dto.*;
import com.ecommerce.model.response.ProductResponse;
import com.ecommerce.model.response.Response;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProductAPI {

    private final ProductService productService;
    private final UploadService uploadService;

    @GetMapping("/products/{id}")
    public Response<Map<String, ProductDTO>> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.getProductById(id);
        Map<String, ProductDTO> map = new HashMap<>();
        map.put("product", productDTO);
        return new Response<>("success", map);
    }


    @PostMapping(value = "/admin/products", consumes =  {"multipart/form-data"})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDTO> addOrUpdateProduct(@ModelAttribute @Validated ProductAddDTO productAddDTO) {
            ProductDTO savedProduct = productService.addOrUpdateProduct(productAddDTO);
            return ResponseEntity.ok(savedProduct);
    }

    @GetMapping("/products")
    public Response<ProductResponse> searchProducts(ProductSearchCriteria criteria) {
        ProductResponse response = productService.searchProducts(criteria);
        return new Response<>("success", response);
    }

    @GetMapping("/admin/products")
    public Response<ProductResponse> searchProductsAdmin(ProductSearchCriteriaAdmin criteria) {
        ProductResponse response = productService.searchProductsByAdmin(criteria);
        return new Response<>("success", response);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
//
//    }


}
