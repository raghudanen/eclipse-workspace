Feature: Fetching Movie List

Scenario: Verify Movie List is displayed

	Given URL and Query Parameters 
	When user calls movieListAPI with GET http request
	Then status in response body is OK

