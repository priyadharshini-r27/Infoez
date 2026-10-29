
@MyReview
Feature: Infoez My Reviews Page Automation and Validation

 Scenario: Navigate to My Reviews 
 Given User is on the Business dashboard page
 When User clicks on the profile menu at top right
 When user clicks on "My Reviews" option in the profile dropdown
 Then user is redirected to the My Reviews page with URL containing "my-reviews"
 And the My Reviews page displays subheading "CONTRIBUTIONS" and main heading "Your reviews"
 
