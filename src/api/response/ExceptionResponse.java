package api.response;

import api.Exception.YelpRequestException;

public class ExceptionResponse extends Response{
    int statusCode;
    String URL;
    public ExceptionResponse(String response, int statusCode, String url) {
        super(response);
        this.statusCode = statusCode;
        this.URL = url;
    }

    public YelpRequestException getYelpException() {
        return new YelpRequestException(statusCode, URL, parser.parseError(response));
    }
}
