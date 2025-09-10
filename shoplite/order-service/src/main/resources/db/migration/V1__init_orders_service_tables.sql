CREATE TABLE IF NOT EXISTS orders.orders (
    id            BIGSERIAL PRIMARY KEY,
    customer_email VARCHAR(255) NOT NULL,
    status         VARCHAR(32)  NOT NULL,
    total_amount   NUMERIC(19,2) NOT NULL,
    created_at     TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS orders.order_items (
    id         BIGSERIAL PRIMARY KEY,
    order_id   BIGINT NOT NULL REFERENCES orders.orders(id) ON DELETE CASCADE,
    product_id BIGINT NOT NULL,
    quantity   INT NOT NULL,
    unit_price NUMERIC(19,2) NOT NULL
);