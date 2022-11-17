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

-- Todo where has the MySQL gone? Perhaps as deleted docker container, it went?
-- Didn't actually run it from here before

-- already ran in MySQL