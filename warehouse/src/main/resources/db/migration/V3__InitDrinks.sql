CREATE TABLE drinks(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR (128),
    quantity INT,
    timestamp TIMESTAMP,
    UNIQUE (name)
);