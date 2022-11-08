
Feature: Database Operations

  Background: : The schema mini_project is available for use by the application
    Given a database connection is established and the person table is not empty

  Scenario: Retrieve firstName and last_name from PEOPLE table
    When I findAll in the database
    Then I am presented with a list of the people in the DB
