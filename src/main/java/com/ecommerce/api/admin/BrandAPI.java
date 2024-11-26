package com.ecommerce.api.admin;

import com.ecommerce.model.dto.BrandDTO;
import com.ecommerce.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/brands")
public class BrandAPI {

    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<BrandDTO> addBrand(@RequestBody BrandDTO brandDTO) {
        return ResponseEntity.ok(brandService.addOrUpdateBrand(brandDTO));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BrandDTO>> getAllBrands() {
        List<BrandDTO> brands = brandService.getAllBrands();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDTO> getBrandById(@PathVariable Long id) {
        BrandDTO brandDTO = brandService.getBrandById(id);
        if (brandDTO != null) {
            return ResponseEntity.ok(brandDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDTO> updateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
        brandDTO.setId(id);  // Ensure the ID is set in the DTO before update
        BrandDTO updatedBrand = brandService.addOrUpdateBrand(brandDTO);
        return ResponseEntity.ok(updatedBrand);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
//        brandService.deleteBrand(id);
//        return ResponseEntity.noContent().build(); // 204 No Content
//    }
}
