package entity;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

public class RestaurantFactory {
    public Restaurant create(String restaurantID){
        OkHttpClient client = new OkHttpClient();
        String apiKey = "5me9OPAUCuB1FfVFrMRp7LICCMeF3AZ36BHVFzMEZhfFj4_-RiJinQmaBiHynwoq75wo_4rrEie2kXKPnFS7qe-tohcLvGyOKnQmBtPPqqhhmgaxTt9s-uFqpBobZXYx";
        Request request = new Request.Builder()
                .url("https://api.yelp.com/v3/businesses/" + restaurantID)
                .header("Authorization", "Bearer " + apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseBody = response.body().string();
                JSONObject jsonObject = new JSONObject(responseBody);
                String restaurantName = jsonObject.getString("name");
                String address = jsonObject.getJSONObject("location").getString("address1");
                String phoneNumber = jsonObject.getString("display_phone");
                JSONArray categoriesJSONArray = jsonObject.getJSONArray("categories");
                ArrayList<String> categories = new ArrayList<>();
                for (int i = 0; i < categoriesJSONArray.length(); i++) {
                    JSONObject jObject = categoriesJSONArray.getJSONObject(i);
                    categories.add(jObject.getString("title"));
                }
                return new Restaurant(restaurantID, restaurantName, address, phoneNumber, categories);
            } else {
                System.err.println("Request failed with error: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}