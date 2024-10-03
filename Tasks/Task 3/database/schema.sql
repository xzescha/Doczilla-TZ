CREATE DATABASE studentdb;
\c studentdb
CREATE TABLE todolist (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    completed BOOLEAN DEFAULT FALSE
);
