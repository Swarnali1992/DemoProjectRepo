package utils;

import java.io.IOException;

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

    // Send GET request
   // @SuppressWarnings("deprecation")
	public static CloseableHttpResponse sendGetRequest(String url) throws IOException {
    	HttpGet getRequest = new HttpGet(url);
        CloseableHttpResponse response=httpClient.execute(getRequest);
        return response;
    }

    // Send POST request
    @SuppressWarnings("deprecation")
	public static CloseableHttpResponse sendPostRequest(String url, String body) throws IOException {
        HttpPost postRequest = new HttpPost(url);
        postRequest.setEntity(new StringEntity(body));
        postRequest.setHeader("Content-Type", "application/json");
        return httpClient.execute(postRequest);
    }

    // Parse response body
    public static String parseResponse(CloseableHttpResponse response) throws IOException, ParseException {
        return EntityUtils.toString(response.getEntity());
    }

    // Validate status code
    public static boolean validateStatusCode(CloseableHttpResponse response, int expectedStatusCode) {
        return response.getCode() == expectedStatusCode;
    }
}
