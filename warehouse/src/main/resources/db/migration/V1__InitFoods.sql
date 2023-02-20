CREATE TABLE foods(
   id BIGINT PRIMARY KEY,
   name VARCHAR (128),
   timestamp TIMESTAMP,
   UNIQUE (name)
);