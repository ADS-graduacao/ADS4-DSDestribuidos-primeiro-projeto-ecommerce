package com.senai.product_api.controller;

import com.senai.product_api.dto.ProductDTO;
import com.senai.product_api.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getProducts() {
        return productService.getAll();
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDTO> getProductByCategory(
            @PathVariable Long categoryId) {

        return productService.getProductByCategoryId(categoryId);
    }

    @GetMapping("/pageable")
    public Page<ProductDTO> getProductsPage(Pageable pageable) {
        return productService.getAllPage(pageable);
    }

    @GetMapping("/{productIdentifier}")
    public ProductDTO findById(
            @PathVariable String productIdentifier) {

        return productService.findByProductIdentifier(productIdentifier);
    }

    @PostMapping
    public ProductDTO newProduct(
            @Valid @RequestBody ProductDTO productDTO) {

        return productService.save(productDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PostMapping("/{id}")
    public ProductDTO editProduct(
            @PathVariable Long id,
            @RequestBody ProductDTO productDTO) {

        return productService.editProduct(id, productDTO);
    }
}