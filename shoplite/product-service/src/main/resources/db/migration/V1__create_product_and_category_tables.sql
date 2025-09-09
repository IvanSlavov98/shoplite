CREATE TABLE IF NOT EXISTS product.categories (
    id       BIGSERIAL PRIMARY KEY,
    type     VARCHAR(64) NOT NULL UNIQUE,
    name     VARCHAR(255) NOT NULL UNIQUE,
    del_flag BOOLEAN NOT NULL DEFAULT FALSE
    );

CREATE TABLE IF NOT EXISTS product.products (
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    stock       INTEGER NOT NULL,
    price       NUMERIC(10,2) NOT NULL,
    category_id BIGINT NOT NULL REFERENCES product.categories(id),
    del_flag    BOOLEAN NOT NULL DEFAULT FALSE
    );

CREATE INDEX IF NOT EXISTS idx_product_del_flag  ON product.products(del_flag);
CREATE INDEX IF NOT EXISTS idx_category_del_flag ON product.categories(del_flag);