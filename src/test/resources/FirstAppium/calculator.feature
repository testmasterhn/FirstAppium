Feature: The addition function on Calculator app

  Scenario Outline: Show corresponding result of additon
    Given I am staying calculator app
    When I input the <a> and number <b> 
    Then I should see the result <c> of addition calculate
	Examples:
		| a	|	b 	| c 	|
		| 5	| 9	|	14	|