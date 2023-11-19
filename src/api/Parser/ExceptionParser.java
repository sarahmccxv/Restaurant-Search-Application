package api.Parser;

import org.json.JSONException;
import org.json.JSONObject;

public class ExceptionParser {
    public static String parseFrom(JSONObject jsonObject) throws JSONException {
        return jsonObject.getJSONObject("error").getString("description");
    }
}