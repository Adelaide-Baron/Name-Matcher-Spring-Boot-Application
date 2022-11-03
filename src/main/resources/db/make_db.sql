CREATE DATABASE mini_project;

USE mini_project;

CREATE TABLE IF NOT EXISTS `PEOPLE` (
`FIRST_NAME` VARCHAR(50),
`SURNAME` VARCHAR(50)
);

INSERT INTO PEOPLE
(FIRST_NAME, SURNAME)
VALUES
("Joe", "Bloggs"),
("James", "Smith");

-- already ran in MySQL