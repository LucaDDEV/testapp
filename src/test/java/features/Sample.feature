Feature: Favorite Section
  @search
  Scenario: Add Recipe Steps
  as a user when I add a recipe as a favorite,
  I should find it in the favorites section.

    Given I am in the app main
    And Tap on recipe "Kiwi Cutie"
    And Tap on Favourite Button
    And Go to Main menu
    Then I should see "Kiwi Cutie" into favorite section
