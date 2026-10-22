@Favorites
Feature: Favorites Page Functionality

@ValidateFavorites
Scenario: Successfully navigate to Favorites and validate saved cards
Given User is on the dashboard page
When User clicks on the user menu at top right
And User clicks on Favorites from the dropdown menu
Then User verifies the Favorites page is loaded with heading "Your favorites"
