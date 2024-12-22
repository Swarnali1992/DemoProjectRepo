package testAPI;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import baseAPI.BaseTestAPI;
import utils.APIUtils;
import utils.Config;

public class RestAPITest extends BaseTestAPI {
	
  @Test(priority=0)
  public void testGetUser() throws IOException, ParseException{
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
      
      @Test(priority=1)
      public void testCreateUser() throws IOException, ParseException{
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
          Assert.assertTrue(APIUtils.validateStatusCode(response,RESPONSE_STATUS_CODE_200 ), "Status code is not 201");
          // Validate the response Body          
          Assert.assertTrue(responseBody.contains("\"name\": \"John Doe\""), "Response does not contain expected name");
      }
      
        
  }

