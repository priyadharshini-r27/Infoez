Feature: Add Business Functionality

Scenario Outline: Successfully add a new business
Given User is on the Business dashboard page
When User clicks on the My Businesses button
And  User clicks on the Add Business button
And User verify the Add Business navigation
And User enters new business name "<businessName>"
And User enters new business description "<description>"
And User enters new business city "<city>"
And User enters new business address "<address>"
And User enters new business phone "<phone>"
And User enters new business.email "<email>"
Then User clicks on submit new business button


Examples:
| businessName          | description                     | city      | address           | phone      | email |
| Aroma Cafe            | Fresh coffee and tasty snacks   | Bengaluru | 45 Indiranagar Rd | 9876543210 | contact@urbancafe.com |