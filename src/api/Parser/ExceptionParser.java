package api.Parser;

import org.json.JSONException;
import org.json.JSONObject;


public class ExceptionParser {
    public String parseFrom(JSONObject jsonObject) {
        try {
            return jsonObject.getJSONObject("error").getString("description");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}