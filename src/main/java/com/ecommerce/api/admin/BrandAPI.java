package com.ecommerce.api.admin;

import com.ecommerce.model.dto.BrandDTO;
import com.ecommerce.model.dto.ProductDTO;
import com.ecommerce.model.response.Response;
import com.ecommerce.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class BrandAPI {

    private final BrandService brandService;

    @PostMapping("/admin/brands")
    public ResponseEntity<BrandDTO> addBrand(@RequestBody BrandDTO brandDTO) {
        return ResponseEntity.ok(brandService.addOrUpdateBrand(brandDTO));
    }

    @GetMapping("/admin/brands")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        List<BrandDTO> brands = brandService.getAllBrands();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/admin/brands/{id}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable Long id) {
        BrandDTO brandDTO = brandService.getBrandById(id);
        if (brandDTO != null) {
            return ResponseEntity.ok(brandDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/admin/brands/{id}")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
        brandDTO.setId(id);  // Ensure the ID is set in the DTO before update
        BrandDTO updatedBrand = brandService.addOrUpdateBrand(brandDTO);
        return ResponseEntity.ok(updatedBrand);
    }


    @GetMapping("/categories/brands")
    public Response<Map<String, List<BrandDTO>>> getBrandByCategory(@RequestParam String categoryName) {
        List<BrandDTO> dtoList = brandService.getBrandByCategory(categoryName);
        Map<String, List<BrandDTO>> map = new HashMap<>();
        map.put("brands", dtoList);
        return new Response<>("success", map);
    }

}
