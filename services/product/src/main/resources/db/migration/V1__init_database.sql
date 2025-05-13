CREATE TABLE IF NOT EXISTS category(
                                       id BIGINT PRIMARY KEY NOT NULL,
                                       name VARCHAR(255),
                                       description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS product (
                                       id INT PRIMARY KEY NOT NULL,
                                       name VARCHAR(255),
                                       description VARCHAR(255),
                                       available_quantity BIGINT NOT NULL,
                                       price NUMERIC(38, 2),
                                       category_id BIGINT,
                                       CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES category(id)
);


CREATE sequence if not exists category_seq increment by 50;
CREATE sequence if not exists product_seq increment by 50;
