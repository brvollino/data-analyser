
CREATE TABLE customer (
 cnpj VARCHAR(16) PRIMARY KEY,
 name VARCHAR(200) NOT NULL,
 business_area VARCHAR(200) NOT NULL
);

CREATE TABLE sale (
 id BIGINT PRIMARY KEY,
 salesman_name VARCHAR(200) NOT NULL,
 total_value NUMERIC(17,2) NOT NULL
);

CREATE TABLE sale_item (
 sale_id BIGINT NOT NULL,
 item_id BIGINT NOT NULL,
 quantity INT NOT NULL,
 unit_price NUMERIC(17,2) NOT NULL,
 PRIMARY KEY(sale_id, item_id),
 FOREIGN KEY(sale_id) REFERENCES sale(id),
);

CREATE TABLE salesman (
 cpf VARCHAR(13) PRIMARY KEY,
 name VARCHAR(200) UNIQUE NOT NULL,
 salary NUMERIC(17,2) NOT NULL
);

CREATE INDEX sale_salesman_name_index ON sale(salesman_name);
CREATE INDEX total_value_index ON sale(total_value);
CREATE INDEX salesman_name_index ON salesman(name);
