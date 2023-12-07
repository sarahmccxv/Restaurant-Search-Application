package api_testing.ExceptionTesting;

import api.Exception.YelpRequestException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class YelpRequestExceptionTest {

    @Test
    public void testExceptionMessageConstruction() {
        int statusCode = 404;
        String uri = "https://api.yelp.com";
        String description = "Resource not found";

        YelpRequestException exception = new YelpRequestException(statusCode, uri, description);

        String expectedMessage = String.format("HTTP Error occurred%nStatus code: %d%nURI: %s%nDescription: %s",
                statusCode, uri, description);

        assertEquals(expectedMessage, exception.getMessage(),
                "Exception message should match the expected format");
    }
}