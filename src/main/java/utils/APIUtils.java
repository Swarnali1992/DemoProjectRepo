package utils;

import java.io.IOException;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

public class APIUtils {
	private static CloseableHttpClient httpClient = HttpClients.createDefault();

    /**
     * Sends an HTTP GET request to the specified URL and returns the response.
     * 
     * This method sends a GET request to the specified URL using Apache HttpClient. It returns the 
     * CloseableHttpResponse object that contains the server's response to the request. 
     * In case of network or protocol errors, appropriate exceptions are thrown.
     * 
     * @param url The URL to which the GET request is to be sent. It should be a valid HTTP/HTTPS URL.
     * @return CloseableHttpResponse The response from the server. You should close this response 
     *         once you're done processing it to release the underlying resources.
     * @throws IOException If an I/O error occurs while sending the request or reading the response. This can
     *         happen due to network issues, server unavailability, or protocol errors.
     * @throws ClientProtocolException If there is a protocol violation when sending the request or receiving
     *         the response. This typically occurs when the server returns a response that does not conform
     *         to HTTP standards.
     *         
     */
	
    // Send GET request
   // @SuppressWarnings("deprecation")
	public static CloseableHttpResponse sendGetRequest(String url) throws IOException, ClientProtocolException {
	
			HttpGet getRequest = new HttpGet(url);
	        CloseableHttpResponse response=httpClient.execute(getRequest);
	        return response;
    }

	
	/**
	 * Sends an HTTP POST request to the specified URL with a JSON body and returns the response.
	 * 
	 * This method creates an HTTP POST request with the provided URL and body. The body is expected to be in JSON format,
	 * and the request includes the appropriate "Content-Type" header set to "application/json". The method executes the
	 * POST request using an HTTP client and returns the response as a {@link CloseableHttpResponse}.
	 * 
	 * @param url The URL to which the POST request is sent. It must be a valid HTTP/HTTPS URL.
	 * @param body The JSON string to be sent as the body of the POST request.
	 * @return A CloseableHttpResponse containing the server's response, including the status code, headers, and
	 *         response content.
	 * @throws IOException If there is an error during the request execution, such as network issues or invalid URL.
	 * @throws ClientProtocolException If there is a protocol violation when sending the request or receiving
     *         the response. This typically occurs when the server returns a response that does not conform
     *         to HTTP standards.
	 * 
	 */
	
	
    // Send POST request
    @SuppressWarnings("deprecation")
	public static CloseableHttpResponse sendPostRequest(String url, String body) throws IOException, ClientProtocolException {
        HttpPost postRequest = new HttpPost(url);
        postRequest.setEntity(new StringEntity(body));
        postRequest.setHeader("Content-Type", "application/json");
        return httpClient.execute(postRequest);
    }
    
    /**
     * Parses the response body from an HTTP response and returns it as a string.
     * 
     * This method takes a CloseableHttpResponse, extracts the entity (the response body),
     * and converts it into a string representation. The response body is typically the content returned
     * by a web server, such as JSON, XML, or plain text.
     * 
     * The method uses EntityUtils#toString(org.apache.http.HttpEntity)} to convert the entity to a string.
     * 
     * @param response The CloseableHttpResponse containing the response from the HTTP request.
     *                 This should not be null.
     * @return A string representing the response body, which may be in JSON, XML, or another text format.
     * @throws IOException If there is an error reading the response entity or converting it to a string.
     * @throws ParseException If there is an error parsing the response entity (if the entity is in a format that requires parsing).
     */
    
    // Parse response body
    public static String parseResponse(CloseableHttpResponse response) throws IOException, ParseException {
        return EntityUtils.toString(response.getEntity());
    }

    
    /**
     * Validates the status code of an HTTP response.
     * 
     * This method compares the status code of the provided {@link CloseableHttpResponse} with the expected status code.
     * It returns true if the actual status code matches the expected one, and false otherwise.
     * 
     * @param response The CloseableHttpResponse object containing the HTTP response.
     *                 This should not be null and must contain a valid status code.
     * @param expectedStatusCode The expected status code (e.g., 200 for OK, 404 for Not Found).
     * @return {@code true} if the status code of the response matches the expected status code, {@code false} otherwise.
     * 
     */
        
    // Validate status code
    public static boolean validateStatusCode(CloseableHttpResponse response, int expectedStatusCode) throws Exception{
        return response.getCode() == expectedStatusCode;
    }
}
