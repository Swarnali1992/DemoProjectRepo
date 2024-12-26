package testAPI;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import baseAPI.BaseTestAPI;
import utils.APIUtils;
import utils.Config;

public class RestAPITest extends BaseTestAPI {
	
	
	
/**
 * Tests the "GET /users/{id}" API endpoint to retrieve a user with a specific ID.
 * 
 * This method performs the following steps:
 * 
 *   Builds the API URL for the GET request using the base API URL and the user ID (e.g., "/users/1").
 *   Sends a GET request to the API using {@link APIUtils#sendGetRequest(String)}.
 *   Logs the API URL and the response status code to the console.
 *   Validates that the response status code is 200 (OK) using {@link APIUtils#validateStatusCode(CloseableHttpResponse, int)}.
 *   Parses the response body using {@link APIUtils#parseResponse(CloseableHttpResponse)} and logs it.
 *   Validates that the response body contains the expected user ID (in this case, user ID 1).
 * 
 * 
 * Any exceptions that occur during the request or validation process are thrown and handled at the caller level.
 * 
 * @throws Exception If there is an error during the API request, response validation, or parsing of the response.
 * @return None
 */
	
  @Test(priority=0)
  public void testGetUser() throws Exception{
	  String apiUrl = apibaseURL + "/users/1";// Example endpoint
           
      // Send GET request
      CloseableHttpResponse response = APIUtils.sendGetRequest(apiUrl);
      System.out.println("The Request Get Url is : " + apiUrl);
      System.out.println("The Response Status Code is : " + response.getCode());
      
      // Validate the status code
      Assert.assertTrue(APIUtils.validateStatusCode(response, RESPONSE_STATUS_CODE_200), "Status code is not 200");
      
      // Parse the response and validate some fields
      String responseBody = APIUtils.parseResponse(response);
      System.out.println("The Reponse Body is : " +responseBody);
      Assert.assertTrue(responseBody.contains("\"id\": 1"), "Response does not contain expected user ID");
  }
     
  /**
   * Tests the "POST /users" API endpoint to create a new user.
   * 
   * This method performs the following steps:
   * 
   *   Builds the API URL for the POST request to the "/users" endpoint using the base API URL.
   *   Creates a request body in JSON format containing the details of the new user (e.g., name, username, email).
   *   Sends a POST request to the API using {@link APIUtils#sendPostRequest(String, String)}.
   *   Logs the API URL, response status code, and response body to the console for debugging and visibility.
   *   Validates that the response status code is 201 (Created) using {@link APIUtils#validateStatusCode(CloseableHttpResponse, int)}.
   *   Validates that the response body contains the expected user name ("John Doe").
   * 
   * 
   * Any exceptions that occur during the request or validation process are thrown and handled at the caller level.
   * 
   * @throws Exception If there is an error during the API request, response validation, or parsing of the response.
   * @return None
   * 
   */
  
  
      @Test(priority=1)
      public void testCreateUser() throws Exception{
    	  String apiUrl = apibaseURL + "/users";

          String body = "{\n" +
                        "  \"name\": \"John Doe\",\n" +
                        "  \"username\": \"johndoe\",\n" +
                        "  \"email\": \"john.doe@example.com\"\n" +
                        "}";

          // Send POST request
          CloseableHttpResponse response = APIUtils.sendPostRequest(apiUrl, body);
          System.out.println("The Request Post Url is : " + apiUrl);
          
          // Parse and validate response
          String responseBody = APIUtils.parseResponse(response);
          System.out.println("The Reponse Body is : " +responseBody); 
          System.out.println("The Response Status Code is : " + response.getCode());
          
          // Validate the status code
          Assert.assertTrue(APIUtils.validateStatusCode(response,RESPONSE_STATUS_CODE_201), "Status code is not 201");
          // Validate the response Body          
          Assert.assertTrue(responseBody.contains("\"name\": \"John Doe\""), "Response does not contain expected name");
      }
      
        
  }

