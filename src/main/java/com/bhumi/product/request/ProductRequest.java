package com.bhumi.product.request;

import com.bhumi.product.model.ProductStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductRequest(

        @NotBlank(message = "SKU is required")
        String sku,

        @NotBlank(message = "Product name is required")
        String name,

        @Size(max = 1000, message = "Description must not exceed 1000 characters")
        String description,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.00", inclusive = false, message = "Price must be greater than zero")
        @Digits(integer = 17, fraction = 2, message = "Price must be a valid monetary amount with up to 2 decimal places")
        BigDecimal price,

        @NotBlank(message = "Currency is required")
        @Pattern(regexp = "^[A-Za-z]{3}$", message = "Currency must be three-letter code")
        String currency,
        ProductStatus status) {
}