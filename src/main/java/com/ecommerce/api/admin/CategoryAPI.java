package com.ecommerce.api.admin;

import com.ecommerce.model.dto.CategoryDTO;
import com.ecommerce.model.dto.ProductDTO;
import com.ecommerce.model.response.ProductResponse;
import com.ecommerce.model.response.Response;
import com.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class    CategoryAPI {

    private final CategoryService categoryService;

    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.addCategory(categoryDTO));
    }

    @GetMapping("/categories")
    public Response<Map<String, List<CategoryDTO>>> getAllCategories() {
        List<CategoryDTO> dtoList = categoryService.getAllCategory();
        Map<String, List<CategoryDTO>> categoryMap = new HashMap<>();
        categoryMap.put("categories", dtoList);
        return new Response<>("success", categoryMap);
    }
}
