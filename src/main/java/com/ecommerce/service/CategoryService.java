package com.ecommerce.service;

import com.ecommerce.model.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO addCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategory();
}
