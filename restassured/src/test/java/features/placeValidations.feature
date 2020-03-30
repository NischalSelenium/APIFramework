Feature: Validating place APIs

@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI

    Given Add place payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "POST" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify "place_id" maps to "<name>" by hitting "getPlaceAPI" with "GET" http request
    
    
Examples:
	|name    |language  |address            |
	|Nischal |English   |World Trade Centre |
#	|Amrutha |Marathi   |Orion Mall         |


@DeletePlace
Scenario: Verify if place is deleted successfully using deletePlaceAPI

    Given deletePlaceAPI payload
    When user calls "deletePlaceAPI" with "POST" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"