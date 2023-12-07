package api_testing.YelpTesting;

import api.Exception.YelpRequestException;
import api.response.ExceptionResponse;
import api.yelp.YelpAPIClient;
import api.yelp.YelpURIs;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class YelpAPIClientTest {

    private YelpAPIClient yelpAPIClient;
    private Response response;
    private ResponseBody responseBody;

    @BeforeEach
    public void setUp() {
        // Mock objects
        YelpURIs yelpURIs = mock(YelpURIs.class);
        yelpAPIClient = new YelpAPIClient(yelpURIs);
        response = mock(Response.class);
        responseBody = mock(ResponseBody.class);
    }

    @Test
    public void testCheckStatus_SuccessfulResponse() throws IOException {
        // Set up a successful response with code 200
        when(response.code()).thenReturn(200);

        // Call the method to check status
        yelpAPIClient.response = response;
        yelpAPIClient.checkStatus("http://example.com");

        // Verify that no exception is thrown
        // In this case, no exception should be thrown for a successful response
    }

    @Test
    public void testCheckStatus_UnsuccessfulResponse() throws IOException {
        // Set up an unsuccessful response with code 404
        when(response.code()).thenReturn(404);
        when(response.body()).thenReturn(responseBody);
        when(responseBody.string()).thenReturn("Not Found");

        // Mocking the ExceptionResponse and YelpRequestException
        ExceptionResponse exceptionResponse = mock(ExceptionResponse.class);
        when(exceptionResponse.getYelpException()).thenReturn(new YelpRequestException(404, "http://example.com", "Not Found"));
    }
}
