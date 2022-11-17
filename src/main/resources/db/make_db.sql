CREATE DATABASE mini_project;

USE mini_project;

CREATE TABLE IF NOT EXISTS persons (
    firstName VARCHAR(50),
    surname VARCHAR(50),
    id INT
    );

INSERT INTO persons
(firstName, surname, id)
VALUES
    ("Joe", "Bloggs", 1),
    ("James", "Smith", 2);
