
@CreateAccount
Feature: Create Account functionality



Scenario: Create Business account
Given the user navigates to the Infoez login page
When the user clicks on the "Create one" link
When the user selects the "Business" account type option
And the user enters the full name "<fullName>"
And the user enters the email "<email>"
And the user enters the phone number "<phone>"
And the user enters the password "<password>"
And the user clicks on the "Create account" button
Then the page outcome should match "<expected>"
Then user validates registration input fields


 Examples:
 | fullName      | email           | phone       | password         | result      | 
 | Sam           | sam@gmail.com   | 1234567890  | Samr@567         | valid       |