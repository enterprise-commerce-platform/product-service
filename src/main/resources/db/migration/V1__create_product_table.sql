CREATE TABLE products
(
    id          UUID PRIMARY KEY,
    sku         VARCHAR(64)  NOT NULL,
    name        VARCHAR(200) NOT NULL,
    description TEXT,
    price       NUMERIC(19, 2) NOT NULL,
    currency    VARCHAR(3)   NOT NULL,
    status      VARCHAR(30)  NOT NULL,
    version     BIGINT       NOT NULL DEFAULT 0,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITH TIME ZONE NOT NULL,

    CONSTRAINT uk_products_sku UNIQUE (sku),
    CONSTRAINT chk_products_price_non_negative CHECK (price >= 0)
);

CREATE INDEX idx_products_name
    ON products (name);

CREATE INDEX idx_products_status
    ON products (status);