CREATE TABLE product_entity (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(255),
    description TEXT,
    price NUMERIC(10, 2),
    category_id BIGINT REFERENCES category_entity(id)
);