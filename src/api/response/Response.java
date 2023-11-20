package api.response;

import api.Parser.JSONParser;
import api.Parser.Parser;

public class Response {
    public final String response;
    public Parser parser;

    public Response(String response) {
        this.response = response;
        this.parser = new JSONParser();
    }

    public String getResponse() {
        return response;
    }
}