package api.Parser;

import org.json.JSONException;
import org.json.JSONObject;

public class ErrorParser {
    public static String parseFrom(JSONObject jsonObject) throws JSONException {
        return jsonObject.getJSONObject("error").getString("description");
    }
}