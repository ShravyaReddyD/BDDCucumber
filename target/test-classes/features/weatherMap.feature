Feature: Validating Weather Station APIs

  Scenario: Verify register station API without API Key throws error negative test
    Given weather station API endpoint
    When user calls registerStationAPI with post http request
    Then the API call should give status code 401
    And the test fail to match the expected message

  Scenario: Verify register station API without API Key throws error
    Given weather station API endpoint
    When user calls registerStationAPI with post http request
    Then the API call should give status code 401
    And the response message body should match the expected message

  Scenario Outline: Register two weather stations
    Given weather station API endpoint
    When a request is made to register a weather station with details <externalId>,<name>,<latitude>,<longitude> and <altitude>
    Then the API call should give status code 201

    Examples: 
      | externalId   | name                       | latitude | longitude | altitude |
      | DEMO_TEST001 | Team Demo Test Station 001 |    33.33 |   -122.43 |      222 |
      | DEMO_TEST002 | Team Demo Test Station 002 |    44.44 |   -122.44 |      111 |

  Scenario Outline: Get the details of registered stations
    Given weather station API endpoint
    When a rquest is made to get all the registered stations
    Then the API call should give status code 200
    And the response should get the same details sent in registration request <externalId> and <name>

    Examples: 
      | externalId   | name                       | latitude | longitude | altitude |
      | DEMO_TEST001 | Team Demo Test Station 001 |    33.33 |   -122.43 |      222 |
      | DEMO_TEST002 | Team Demo Test Station 002 |    44.44 |   -122.44 |      111 |
