package data_access;

import entity.RestaurantFactory;
import entity.Restaurant;
import entity.Review;

import okhttp3.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestaurantDataAccessObject {
    private static final String API_URL = "https://api.yelp.com/v3/businesses/";
    private static final String API_TOKEN = "5me9OPAUCuB1FfVFrMRp7LICCMeF3AZ36BHVFzMEZhfFj4_-RiJinQmaBiHynwoq75wo_4rrEie2kXKPnFS7qe-tohcLvGyOKnQmBtPPqqhhmgaxTt9s-uFqpBobZXYx";
    public RestaurantFactory restaurantFactory;

    public RestaurantDataAccessObject(RestaurantFactory restaurantFactory) {
        this.restaurantFactory = restaurantFactory;
    }

    public static String getApiToken() {
        return API_TOKEN;
    }


    public ArrayList<Restaurant> getLocalRestaurant(String locationName) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("%ssearch?location=%s", API_URL, locationName))
                .addHeader("Authorization", String.format("Bearer %s", API_TOKEN))
                .build();
        try (Response response = client.newCall(request).execute()) {
            ArrayList<Restaurant> restaurantsList = new ArrayList<>();
            Map<String, Object> restaurantMap = convertJsonToMap(response.body().string());
            List<Map<String, Object>> businessesList = (List<Map<String, Object>>) restaurantMap.get("businesses");
            for (Map<String, Object> businessMap : businessesList) {
                String restaurantID = (String) businessMap.get("id");
                String restaurantName = (String) businessMap.get("name");

                Map<String, String> addressMap = (Map<String, String>) businessMap.get("address");
                String address = addressMap.get("address1");

                String phoneNumber = (String) businessMap.get("display_phone");

                List<Map<String, String>> categoriesList = (List<Map<String, String>>) businessMap.get("categories");
                ArrayList<String> categories = new ArrayList<>();
                for (Map<String, String> categoriesMap : categoriesList) {
                    categories.add(categoriesMap.get("title"));
                }

                restaurantsList.add(restaurantFactory.create(restaurantID, restaurantName ,address, phoneNumber, categories));
            }
            return restaurantsList;
        }
    }

    public Restaurant getRestaurantByID(String restaurantID) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("%s%s", API_URL, restaurantID))
                .addHeader("Authorization", String.format("Bearer %s", API_TOKEN))
                .build();
        try (Response response = client.newCall(request).execute()) {
            Map<String, Object> restaurantMap = convertJsonToMap(response.body().string());
            String restaurantName = (String) restaurantMap.get("name");
            String phoneNumber = (String) restaurantMap.get("display_phone");

            Map<String, String> addressMap = (Map<String, String>) restaurantMap.get("address");
            String address = addressMap.get("address1");

            List<Map<String, String>> categoriesList = (List<Map<String, String>>) restaurantMap.get("categories");
            ArrayList<String> categories = new ArrayList<>();
            for (Map<String, String> categoriesMap : categoriesList) {
                categories.add(categoriesMap.get("title"));
            }

            return restaurantFactory.create(restaurantID, restaurantName, address, phoneNumber, categories);
        }
    }

     private static Map<String, Object> convertJsonToMap(String jsonString) {
        Gson gson = new Gson();
        TypeToken<Map<String, Object>> token = new TypeToken<Map<String, Object>>() {};
        return gson.fromJson(jsonString, token.getType());
    }
}
