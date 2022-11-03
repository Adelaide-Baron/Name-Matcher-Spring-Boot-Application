Feature: Application Health

  Scenario: Actuator Health
    When a GET request is performed on "actuator/health"
    Then the response code is "200"
    And the response contains "status" with value "UP"


