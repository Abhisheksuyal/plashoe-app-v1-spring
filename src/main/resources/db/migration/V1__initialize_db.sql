CREATE TABLE product_entity (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
   product_name VARCHAR(255),
   description VARCHAR(255),
   quantity INTEGER,
   price DOUBLE PRECISION,
   CONSTRAINT pk_productentity PRIMARY KEY (id)
);