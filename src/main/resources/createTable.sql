DROP DATABASE IF EXISTS carrentaldb;
CREATE DATABASE carrentaldb;
\connect carrentaldb;

-- Drop the tables if they already exist
DROP TABLE IF EXISTS applicationUser CASCADE;
DROP TABLE IF EXISTS car CASCADE;
DROP TABLE IF EXISTS rental CASCADE;

-- Create the users table
CREATE TABLE applicationUser
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    type     VARCHAR(10)  NOT NULL CHECK (type IN ('customer', 'agent'))
);


-- Create the cars table
CREATE TABLE car
(
    id           SERIAL PRIMARY KEY,
    model        VARCHAR(255)   NOT NULL UNIQUE,
    brand        VARCHAR(255)   NOT NULL,
    year         INTEGER        NOT NULL,
    rental_fee   NUMERIC(10, 2) NOT NULL,
    offer_fee    NUMERIC(10, 2),
    availability BOOLEAN        NOT NULL
);

-- Create the rental table
CREATE TABLE rental
(
    id         SERIAL PRIMARY KEY,
    customer   INTEGER        NOT NULL REFERENCES applicationUser (id),
    car        INTEGER        NOT NULL REFERENCES car (id),
    start_date DATE           NOT NULL,
    end_date   DATE           NOT NULL,
    total_fee  NUMERIC(10, 2) NOT NULL
);


-- Insert data into the users table
INSERT INTO applicationUser (username, password, email, type)
VALUES ('john_doe', 'password123', 'johndoe@example.com', 'customer');
INSERT INTO applicationUser (username, password, email, type)
VALUES ('jane_doe', 'password456', 'janedoe@example.com', 'customer');
INSERT INTO applicationUser (username, password, email, type)
VALUES ('agent_1', 'password789', 'agent1@example.com', 'agent');

-- Insert data into the cars table
INSERT INTO car (model, brand, year, rental_fee, availability)
VALUES ('Model S', 'Tesla', 2020, 100.00, true);
INSERT INTO car (model, brand, year, rental_fee, availability)
VALUES ('Model 3', 'Tesla', 2018, 80.00, true);
INSERT INTO car (model, brand, year, rental_fee, availability)
VALUES ('Model X', 'Tesla', 2016, 90.00, false);
INSERT INTO car (model, brand, year, rental_fee, offer_fee, availability)
VALUES ('Yaris', 'Toyota', 2010, 90.00, 40.00, false);

-- Insert data into the rentals table
INSERT INTO rental (customer, car, start_date, end_date, total_fee)
VALUES (1, 1, '2022-01-01', '2022-01-07', 700.00);
INSERT INTO rental (customer, car, start_date, end_date, total_fee)
VALUES (2, 2, '2022-02-01', '2022-02-07', 560.00);
INSERT INTO rental (customer, car, start_date, end_date, total_fee)
VALUES (1, 3, '2022-03-01', '2022-03-07', 630.00);