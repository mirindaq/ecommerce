package com.ecommerce.api.admin;


import com.ecommerce.model.dto.ProductDTO;
import com.ecommerce.model.dto.ProductSearchCriteria;
import com.ecommerce.model.response.ProductResponse;
import com.ecommerce.model.response.Response;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProductAPI {

    private final ProductService productService;
    private final UploadService uploadService;

    @GetMapping("/admin/products")
    public Response<ProductResponse> getAllProducts() {
        List<ProductDTO> dtoList = productService.getAllProducts();
        ProductResponse response = new ProductResponse();
        response.setProducts(dtoList);
        return new Response<>("success", response);
    }

    @GetMapping("/products/{id}")
    public Response<Map<String, ProductDTO>> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.getProductById(id);
        Map<String, ProductDTO> map = new HashMap<>();
        map.put("product", productDTO);
        return new Response<>("success", map);
    }


    @PostMapping("/admin/products")
    public ResponseEntity<ProductDTO> addOrUpdateProduct(@RequestBody ProductDTO productDTO) {
            ProductDTO savedProduct = productService.addOrUpdateProduct(productDTO);
            return ResponseEntity.ok(savedProduct);
    }
    @PostMapping("/admin/products/image")
    public ResponseEntity<Response<List<String>>> addImage(@RequestParam("image") List<MultipartFile> images) {
        Response<List<String>> response = new Response<>(

                "Images uploaded successfully",
                uploadService.handleSaveUploadFile(images, "product")
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products")
    public Response<ProductResponse> searchProducts(ProductSearchCriteria criteria) {
        List<ProductDTO> dtoList = productService.searchProducts(criteria);
        ProductResponse response = new ProductResponse();
        response.setProducts(dtoList);
        return new Response<>("success", response);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
//
//    }


}
