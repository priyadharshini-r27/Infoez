Feature: Infoez Subscription Page Automation and Validation

Scenario: Navigate to Subscription from Dashboard and validate pricing plans
Given User is on the Business dashboard page
When User clicks on the profile menu at top right
And user selects Subscription option from the profile dropdown menu
Then user is redirected to the Subscription page with URL containing "subscription"
And the Subscription page should display pricing heading "Choose your plan"
And all subscription pricing plans "Free", "Starter", "Pro", and "Enterprise" should be visible
And the active plan should be marked as "Pro" with status "Current" and "Active plan" button