
@Profile
Feature: Profile Management Validation

@Profile
Scenario Outline: Successfully navigate to profile and update details
Given User is on the Business dashboard page
When User clicks on the profile menu at top right
And User clicks on Profile from the dropdown menu
Then User verifies the Profile page is loaded with heading "Your profile"
When User updates the profile name to "<name>"
And User updates the profile phone to "<phone>"
And User clicks on Save changes button
Then A profile update success alert should be displayed
And The profile name input should have value "<name>"

Examples:
| name       | phone      |
| Ravi       | 9876543210 |