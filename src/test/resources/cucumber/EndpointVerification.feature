# Created by adelaide.baron at 02/11/2022
Feature: Verify endpoint responses

  Scenario: Hello World
    When a GET request is performed on "HelloWorld"
    Then the response code is "200"
    And the response contains "message" with value "Hello World"
    
  Scenario: Name matcher GET request with match
    When a GET request is performed on "NameMatcher/Joe_Bloggs"
    Then the response code is "200"
    And the response contains "Match_found" with value "MATCHED"

  Scenario: Name matcher GET request no match
    When a GET request is performed on "NameMatcher/adelaide_baron"
    Then the response code is "200"
    And the response contains "Match_found" with value "NOT_MATCHED"

