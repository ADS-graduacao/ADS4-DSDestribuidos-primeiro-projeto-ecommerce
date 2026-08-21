package com.senai.product_api.service;

import com.senai.product_api.dto.CategoryDTO;
import com.senai.product_api.model.Category;
import com.senai.product_api.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDTO::convert)
                .toList();
    }

    public CategoryDTO findById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return CategoryDTO.convert(category);
    }
}