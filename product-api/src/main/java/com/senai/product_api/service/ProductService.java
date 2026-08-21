package com.senai.product_api.service;

import com.senai.product_api.dto.ProductDTO;
import com.senai.product_api.model.Category;
import com.senai.product_api.model.Product;
import com.senai.product_api.repository.CategoryRepository;
import com.senai.product_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<ProductDTO> getAll() {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(ProductDTO::convert)
                .toList();
    }

    public List<ProductDTO> getProductByCategoryId(Long categoryId) {

        List<Product> products =
                productRepository.getProductByCategory(categoryId);

        return products.stream()
                .map(ProductDTO::convert)
                .toList();
    }

    public ProductDTO findByProductIdentifier(String productIdentifier) {

        Product product =
                productRepository.findByProductIdentifier(productIdentifier);

        if (product != null) {
            return ProductDTO.convert(product);
        }

        return null;
    }

    public ProductDTO save(ProductDTO productDTO) {

        Category category = categoryRepository
                .findById(productDTO.getCategoryDTO().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = Product.convert(productDTO);

        product.setCategory(category);

        product = productRepository.save(product);

        return ProductDTO.convert(product);
    }

    public void delete(long productId) {

        Optional<Product> product =
                productRepository.findById(productId);

        if (product.isPresent()) {
            productRepository.delete(product.get());
        }
    }

    public ProductDTO editProduct(long id, ProductDTO dto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (dto.getNome() != null && !dto.getNome().isEmpty()) {
            product.setNome(dto.getNome());
        }

        if (dto.getPreco() != null) {
            product.setPreco(dto.getPreco());
        }

        return ProductDTO.convert(
                productRepository.save(product)
        );
    }

    public Page<ProductDTO> getAllPage(Pageable page) {

        Page<Product> products = productRepository.findAll(page);

        return products.map(ProductDTO::convert);
    }
}
