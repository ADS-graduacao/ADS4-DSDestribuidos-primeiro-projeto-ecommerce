package com.senai.product_api.model;

import com.senai.product_api.dto.CategoryDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    public static Category convert(CategoryDTO categoryDTO) {

        Category category = new Category();

        category.setId(categoryDTO.getId());
        category.setNome(categoryDTO.getNome());

        return category;
    }
}
