package com.bhumi.product.response;

import com.bhumi.product.model.Product;
import com.bhumi.product.model.ProductStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record ProductResponse(UUID id, String sku, String name, String description, BigDecimal price, String currency, ProductStatus status, long version,
                              OffsetDateTime createdAt, OffsetDateTime updatedAt) {

    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getSku(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCurrency(),
                product.getStatus(),
                product.getVersion(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
