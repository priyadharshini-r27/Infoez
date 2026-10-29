Feature: Infoez Explore Page

Scenario: Search shop by shop name
Given user is on the Explore Page
When Business owner click the Explore module
And Business owner enter "<Shop Name>" in shop search field
Then verify the "<status>" valid "<expected>" details


Examples:
| Shop Name   | status | expected               |
|Aroma Cafe   | valid  | Aroma Cafe             |
| ARV         | invalid| No businesses found    |


Scenario: Search shop by place
Given user is on the Explore Page
When Business owner click the Explore module
And Business owner enter the shop place "<place>" search field
Then Business owner should see shop from place "<place>" with "<Status>" and expected "<expected>"



Examples:
| place   | Status | expected                 |
| Bengaluru  | valid  | Bengaluru                 |
|Thanjavur| invalid| No businesses found      |

Scenario: Validate Explore page filters
Given user is on the Explore Page
When Business owner click the Explore module
When user selects category filter "<Category>"
And user selects sort by filter "<SortBy>"
And user selects minimum rating filter "<MinRating>"
Then filtered business listings should be displayed successfully

Examples:
| Category         | SortBy         | MinRating |
| Restaurants      | Top rated      | 4.0+      |
| Cafes & Bakeries | Most popular   | 4.5+      |
| Beauty & Spa     | Recently added | Any       |



