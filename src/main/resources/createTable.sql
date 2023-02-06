DROP DATABASE IF EXISTS carrentaldb;
CREATE DATABASE carrentaldb;
\connect carrentaldb;

-- Insert data into the users table
INSERT INTO applicationUser (username, password, email, user_type)
VALUES ('john_doe', 'password123', 'johndoe@example.com', 'customer');
INSERT INTO applicationUser (username, password, email, user_type)
VALUES ('jane_doe', 'password456', 'janedoe@example.com', 'customer');
INSERT INTO applicationUser (username, password, email, user_type)
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

