Feature: Validating Place APIs
@Adp 
Scenario Outline: Verify if place is successfully added using AddPlaceAPI
	 Given Add place payload with "<name>" "<language>" "<address>"
	 When user calls "addPlaceAPI" with "POST" http request
	 Then the API call is successfully completed with status code 200 
	 And "status" in response body is "OK"
	 And "scope" in response body is "APP"
	 And verify place_Id created maps to "<name>" using "getPlaceAPI"
	 
Examples:
	 |name 				| language 		|address					|
	 |aaaHouse			|	English 	| World class Centre		|
#	 |bbHouse			|	Spanish 	| Sea class Centre			|
#	 |Frontline house	|French-EN		|29, side layout, cohen 09	|
	
@Dep
Scenario: Verify if delete place functionality is working

	Given DeletePlace payload 
	When user calls "deletePlaceAPI" with "POST" http request
	Then the API call is successfully completed with status code 200
	And "status" in response body is "OK"