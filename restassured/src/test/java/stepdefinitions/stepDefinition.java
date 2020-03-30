package stepdefinitions;
  
import java.io.FileNotFoundException;
/*import java.util.ArrayList;
import java.util.List;*/
import java.io.IOException;

import static org.junit.Assert.*;
//import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.cucumber.java.en.And;
//import io.cucumber.java.en.And;
import io.cucumber.java.en.Given; 
import io.cucumber.java.en.Then; 
import io.cucumber.java.en.When;
//import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import resources.APIResources;
/*import pojo.AddPlace;
import pojo.Location;*/
import resources.TestDataBuild;
import resources.Utils;
  
  public class stepDefinition extends Utils {
	  RequestSpecification res;
	  ResponseSpecification resspec;
	  Response response;
	  TestDataBuild data = new TestDataBuild();
	  
  @Given("Add place payload with {string} {string} {string}") 
  public void add_place_payload(String name, String language, String address) throws IOException { 
	  
      
	  resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	  res=given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
  
  }
  
  @When("user calls {string} with {string} http request") 
  public void user_calls_with_post_http_request(String resource,String method) { 
	  
	  APIResources resourceAPI=APIResources.valueOf(resource);
	  System.out.println(resourceAPI.getResource());
	  
	  if(method.equalsIgnoreCase("POST"))
	  {
		  response=res.when().post(resourceAPI.getResource());
	  }
	  else if(method.equalsIgnoreCase("GET"))
	  {
		  response=res.when().get(resourceAPI.getResource());
	  }
	  //response =res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();
  
  }
  
  @Then("the API call got success with status code {int}") 
  public void the_API_call_got_success_with_status_code(Integer int1) { 
	  
	 assertEquals(response.getStatusCode(),200);
  
  }
  
  @And("{string} in response body is {string}") 
  public void in_response_body_is(String keyValue, String expectedValue) 
  { 
	  
	 assertEquals(getJsonPath(response,keyValue),expectedValue);
  }
	  
  @And("verify {string} maps to {string} by hitting {string} with {string} http request")
	  public void verify_place_id_created_maps_to_using(String expectedKey,String expectedName,String resource,String method) throws IOException 
    {
	
	  String place_id=getJsonPath(response,expectedKey);
	  res=given().spec(requestSpecification()).queryParam(expectedKey, place_id);
	  user_calls_with_post_http_request(resource,method);
      String actualName=getJsonPath(response,"name");
      assertEquals(actualName,expectedName);
    }
  
  } 