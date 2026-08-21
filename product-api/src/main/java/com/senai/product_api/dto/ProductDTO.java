package com.senai.product_api.dto;

import com.senai.product_api.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotBlank
    private String productIdentifier;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    private Float preco;

    @NotNull
    private CategoryDTO categoryDTO;

    public static ProductDTO convert(Product product) {

        ProductDTO productDTO = new ProductDTO();

        productDTO.setNome(product.getNome());
        productDTO.setPreco(product.getPreco());
        productDTO.setProductIdentifier(product.getProductIdentifier());
        productDTO.setDescricao(product.getDescricao());

        if (product.getCategory() != null) {
            productDTO.setCategoryDTO(
                    CategoryDTO.convert(product.getCategory())
            );
        }

        return productDTO;
    }
}