
@Notification
Feature: Infoez Notifications Page Automation and Validation

Scenario: Navigate to Notifications from Dashboard and validate notifications page
Given User is on the Business dashboard page
When User clicks on the profile menu at top right
And user selects Notifications option from the profile dropdown menu
Then user is redirected to the Notifications page with URL containing "notifications"
And the Notifications page main heading should be displayed
