package com.bhumi.product.service;

import com.bhumi.product.exception.ProductAlreadyExistsException;
import com.bhumi.product.exception.ProductNotFoundException;
import com.bhumi.product.model.Product;
import com.bhumi.product.repository.ProductRepository;
import com.bhumi.product.request.ProductRequest;
import com.bhumi.product.response.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse createProduct(ProductRequest request) {
        String normalizeSku = request.sku().strip().toUpperCase(Locale.ROOT);

        if (productRepository.existsBySku(normalizeSku)) {
            throw new ProductAlreadyExistsException("Product with SKU " + normalizeSku + " already exists.");
        }

        Product product=new Product(
                normalizeSku,
                request.name(),
                request.description(),
                request.price(),
                request.currency()
        );
        try {
            Product savedProduct = productRepository.saveAndFlush(product);
            return ProductResponse.from(savedProduct);
        } catch (DataIntegrityViolationException e) {
            throw new ProductAlreadyExistsException("Product with SKU " + normalizeSku + " already exists.");
        }

    }

    @Transactional(readOnly = true)
    public List<ProductResponse> getProducts() {
        List<Product> list= productRepository.findAll();
        return list.stream().map(ProductResponse::from).toList();
    }

    public ProductResponse getProductById(UUID productId) {
        Product product= productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException(productId));
        return ProductResponse.from(product);

    }

    public ProductResponse updateProduct(@Valid ProductRequest productRequest) {

        Product product= productRepository.findBySku(productRequest.sku()).orElseThrow(()->new ProductNotFoundException(productRequest.sku()));

                product.setName(productRequest.name());
                product.setStatus(productRequest.status());
        productRepository.saveAndFlush(product);
        return ProductResponse.from(product);
    }
}
