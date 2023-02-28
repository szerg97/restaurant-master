CREATE TABLE drinks(
    id BIGINT PRIMARY KEY,
    name VARCHAR (128),
    timestamp TIMESTAMP,
    UNIQUE (name)
);