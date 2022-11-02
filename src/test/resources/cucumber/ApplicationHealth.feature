Feature: Application Health

  Scenario: Actuator Health
    When a GET request is performed on "actuator/health"
    Then the response code is "200"
    And the response contains "status" with value "UP"

    # not sure if the next belongs in health..., need to check some conventions/naming for these
  Scenario: Message body
    When a GET request is performed on "HelloWorld"
    Then the response code is "200"
    And the response contains "message" with value "Hello World"
