Feature: Testing different request on the gorest application

  Scenario: Check if the gorest application can be accessed by users
    When User sends a GET request to list endpoint
    Then User must get back a valid status code 200

  Scenario Outline: Create a new user & verify if the user is added
    When I create a new user by providing the information name "<name>"  email "<email>" gender "<gender>" status "<status>"
    Then I verify that the user with "<email>" is created
    Examples:
      | name   | email            | gender | status |
      | Prime  | prime1@gmail.com | Male   | Active |
      | Prime2 | prime2@gmail.com | Female | Active |





