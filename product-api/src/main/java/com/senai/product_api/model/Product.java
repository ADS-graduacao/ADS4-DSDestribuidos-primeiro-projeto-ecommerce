package com.senai.product_api.model;

import com.senai.product_api.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private Float preco;

    private String descricao;

    private String productIdentifier;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public static Product convert(ProductDTO productDTO) {

        Product product = new Product();

        product.setNome(productDTO.getNome());
        product.setPreco(productDTO.getPreco());
        product.setDescricao(productDTO.getDescricao());
        product.setProductIdentifier(productDTO.getProductIdentifier());

        if (productDTO.getCategoryDTO() != null) {
            product.setCategory(
                    Category.convert(productDTO.getCategoryDTO())
            );
        }

        return product;
    }
}