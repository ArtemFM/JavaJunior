-- Create table "typeproduct" - type product;
CREATE TABLE IF NOT EXISTS typeproduct(
    id_type SERIAL PRIMARY KEY,
    type_name VARCHAR(255)
); 

-- Create table "products" - base productes;
CREATE TABLE IF NOT EXISTS products(
    id_product SERIAL PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    product_price DECIMAL(10, 2) DEFAULT 0 NOT NULL,
    product_expired_date DATE DEFAULT now() NOT NULL,
    id_type INTEGER REFERENCES typeproduct(id_type)
); 

