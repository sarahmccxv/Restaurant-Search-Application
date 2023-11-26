package api.Exception;


public class YelpRequestException extends RuntimeException{
    public YelpRequestException(int statusCode, String uri, String description) {
        super(String.format("HTTP Error occurred%nStatus code: %d%nURI: %s%nDescription: %s",
            statusCode,
            uri,
            description));
    }
}
