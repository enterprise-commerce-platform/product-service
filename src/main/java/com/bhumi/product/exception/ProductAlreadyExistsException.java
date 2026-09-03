package com.bhumi.product.exception;

public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException(String s) {
        super(s);
    }
}
