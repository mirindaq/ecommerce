package com.ecommerce.api.admin;

import com.ecommerce.model.dto.CategoryDTO;
import com.ecommerce.model.response.Response;
import com.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class    CategoryAPI {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.addCategory(categoryDTO));
    }

//    @GetMapping
//    public Response<CategoryDTO> getAllCategories() {
//
//    }
}
