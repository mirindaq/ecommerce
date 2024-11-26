package com.ecommerce.api.admin;


import com.ecommerce.model.dto.ProductDTO;
import com.ecommerce.model.response.Response;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/products")
public class ProductAPI {

    private final ProductService productService;
    private final UploadService uploadService;

    @PostMapping
    public ResponseEntity<ProductDTO> addOrUpdateProduct(@RequestBody ProductDTO productDTO) {
            ProductDTO savedProduct = productService.addOrUpdateProduct(productDTO);
            return ResponseEntity.ok(savedProduct);

    }
    @PostMapping("/image")
    public ResponseEntity<Response<List<String>>> addImage(@RequestParam("image") List<MultipartFile> images) {
        Response<List<String>> response = new Response<>(

                "Images uploaded successfully",
                uploadService.handleSaveUploadFile(images, "product")
        );
        return ResponseEntity.ok(response);

    }


//    @PutMapping("/{id}")
//    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
//
//    }


}
