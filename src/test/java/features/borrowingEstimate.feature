Feature: Testing the borrowing calculator

  Scenario Outline: Check the borrowing estimate based on income and expenses
    Given user navigates to the calculator page
    When user enters <annualIncome>,<otherIncome>,<livingExpenses>,<homeLoanRepayments>,<otherLoanRepayments>,<otherCommitments> and <creditCardLimits>
    And clicks on the borrow estimate button
    Then the borrowing estimate should be as expected
    And clicking the start over button should clear the form

    Examples: 
      | annualIncome | otherIncome | livingExpenses | homeLoanRepayments | otherLoanRepayments | otherCommitments | creditCardLimits |
      |        80000 |       10000 |            500 |                  0 |                 100 |                0 |            10000 |
      |        80000 |       21000 |            500 |                  0 |                  50 |                0 |            10000 |