Feature: gorest application
  As a user I want to test gorest Application

  Scenario Outline: CRUD Test
    When I create a new user by providing the information name "<name>"  email "<email>" gender "<gender>" status "<status>"
    Then I verify that the user with "<name>" is created
    And I update the user with information name "<name>"  email "<email>" gender "<gender>" status "<status>"
    And I delete the user that created with name "<name>"
    Then The user deleted successfully from the list
    Examples:
      | name   | email            | gender | status |
      | Prime  | prime1@gmail.com | Male   | Active |
      | Prime2 | prime2@gmail.com | Female | Active |





