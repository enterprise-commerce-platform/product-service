package com.bhumi.product.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(UUID productId) {
        super("Product not found with ID: " + productId);
    }

    public ProductNotFoundException(String sku) {
        super("Product not found with sku: " + sku);
    }
}
