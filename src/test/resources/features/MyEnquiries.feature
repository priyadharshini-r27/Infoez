Feature: Infoez My Enquiries Page Automation and Validation

Scenario: Navigate to My Enquiries from Dashboard and validate enquiry cards
Given User is on the Business dashboard page
When User clicks on the profile menu at top right
And user selects "My Enquiries" option from the profile dropdown
Then user is redirected to the My Enquiries page with URL containing "my-enquiries"
And the My Enquiries page displays subheading "SENT" and main heading "Your enquiries"
And the enquiry card displayed