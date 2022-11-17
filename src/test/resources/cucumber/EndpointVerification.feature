
Feature: Verify endpoint responses

  Scenario: Hello World
    When a GET request is performed on "HelloWorld"
    Then the response code is 200
    And the response contains "message" with value "Hello World"
    # Todo update endpointverification.feature to response is "Hello World"
    
  Scenario: Name matcher GET request with match
    When a GET request is performed on "NameMatcher/Joe_Bloggs"
    Then the response code is 200
    And the response contains "Match_found" with value "MATCHED"
    # Todo update AND to

  Scenario: Name matcher GET request no match
    When a GET request is performed on "NameMatcher/adelaide_baron"
    Then the response code is 200
    And the response contains "Match_found" with value "NOT_MATCHED"

    # Todo edge case scenario/s, e.g. long name (exceeding 50 characters), another /, _,
  Scenario: Name matcher GET request with no name
    When a GET request is performed on "NameMatcher/"
    Then the response code is 404
    And an error response is returned
    # ^ HTML in step

  # Todo investigate different request types, e.g. POST - 405
  # adapter has tests for this



