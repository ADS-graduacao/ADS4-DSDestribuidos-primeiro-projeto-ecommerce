CREATE TABLE products.product
(
    id                 BIGSERIAL PRIMARY KEY,
    product_identifier VARCHAR(100) NOT NULL UNIQUE,
    nome               VARCHAR(100) NOT NULL,
    descricao          VARCHAR      NOT NULL,
    preco              FLOAT        NOT NULL,
    category_id        BIGINT       NOT NULL,

    CONSTRAINT fk_product_category
        FOREIGN KEY (category_id)
            REFERENCES products.category (id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

ALTER TABLE products.category
    ADD CONSTRAINT uk_category_nome UNIQUE (nome);