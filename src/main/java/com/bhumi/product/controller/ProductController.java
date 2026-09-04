package com.bhumi.product.controller;

import com.bhumi.product.request.ProductRequest;
import com.bhumi.product.response.ProductResponse;
import com.bhumi.product.service.ProductService;
import com.bhumi.product.utils.ProductConstant;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(ProductConstant.PRODUCTS_ENDPOINT)
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@Valid @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.createProduct(productRequest);

        URI location = URI.create(ProductConstant.PRODUCTS_ENDPOINT + "/" + productResponse.id());

        return ResponseEntity.created(location).body(productResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return   ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable UUID productId) {
        return   ResponseEntity.ok(productService.getProductById(productId));
    }

    @PatchMapping
    public ResponseEntity<ProductResponse> updateProduct(@Valid @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.updateProduct(productRequest));
    }

}
