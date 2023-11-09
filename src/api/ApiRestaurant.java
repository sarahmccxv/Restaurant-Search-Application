package api;

import entity.RestaurantFactory;
import entity.Restaurant;

import okhttp3.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiRestaurant implements ApiRestaurantInterface {
    private static final String API_URL = "https://api.yelp.com/v3/businesses/";
    private static final String API_TOKEN = System.getenv("API_KEY");
    public RestaurantFactory restaurantFactory;

    public ApiRestaurant(RestaurantFactory restaurantFactory) {
        this.restaurantFactory = restaurantFactory;
    }

    /**
     * Get a list of restaurants based on the location.
     *
     * @param locationName The name of the locaiton
     * @return A list of restaurant objects with the specified location, or an empty Arraylist if
     * the api request is unsuccessful.
     */
    public ArrayList<Restaurant> getLocalRestaurants(String locationName) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("%ssearch?location=%s", API_URL, locationName))
                .addHeader("Authorization", String.format("Bearer %s", API_TOKEN))
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.code() == 200) {
                ArrayList<Restaurant> restaurantsList = new ArrayList<>();
                Map<String, Object> restaurantMap = convertJsonToMap(response.body().string());
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> businessesList = (List<Map<String, Object>>) restaurantMap.get("businesses");

                for (Map<String, Object> businessMap : businessesList) {
                    String restaurantID = (String) businessMap.get("id");
                    String name = (String) businessMap.get("name");
                    String address = getAddress(businessMap.get("location"));
                    String phoneNumber = (String) businessMap.get("phone");
                    ArrayList<String> categories = getCategories(businessMap.get("categories"));

                    restaurantsList.add(restaurantFactory.create(restaurantID, name, address, phoneNumber, categories));
                }
                return restaurantsList;
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the restaurant by a unique ID
     *
     * @param restaurantID The ID to the restaurant
     * @return A restaurant object with the specified ID, or null if
     * the api request is unsuccessful.
     */
    public Restaurant getRestaurantByID(String restaurantID) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("%s%s", API_URL, restaurantID))
                .addHeader("Authorization", String.format("Bearer %s", API_TOKEN))
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.code() == 200) {
                Map<String, Object> restaurantMap = convertJsonToMap(response.body().string());

                String restaurantName = (String) restaurantMap.get("name");
                String phoneNumber = (String) restaurantMap.get("phone");
                String address = getAddress(restaurantMap.get("location"));
                ArrayList<String> categories = getCategories(restaurantMap.get("categories"));

                return restaurantFactory.create(restaurantID, restaurantName, address, phoneNumber, categories);
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the restaurant by phone number
     *
     * @param phoneNumber The phone number to the restaurant
     * @return A restaurant object with the specified phone number, or null if
     * the api request is unsuccessful or the phone number isn't for any restaurants.
     */
    public Restaurant getRestaurantByPhoneNumber(String phoneNumber) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("%ssearch/phone?phone=%s", API_URL, phoneNumber))
                .addHeader("Authorization", String.format("Bearer %s", API_TOKEN))
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.code() == 200) {
               Map<String, Object> restaurantMap = convertJsonToMap(response.body().string());
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> businessesList = (List<Map<String, Object>>) restaurantMap.get("businesses");
                if (businessesList.isEmpty()) {
                    return null;
                }

                Map<String, Object> businessMap = businessesList.get(0);
                String restaurantID = (String) businessMap.get("id");
                String name = (String) businessMap.get("name");
                String address = getAddress(businessMap.get("location"));
                ArrayList<String> categories = getCategories(businessMap.get("categories"));

                return restaurantFactory.create(restaurantID, name, address, phoneNumber, categories);
            } else {
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAddress(Object location) {
        @SuppressWarnings("unchecked")
        Map<String, String> addressMap = (Map<String, String>) location;
        return addressMap.get("address1");
    }

    private ArrayList<String> getCategories(Object restaurantMap) {
        @SuppressWarnings("unchecked")
        List<Map<String, String>> categoriesList = (List<Map<String, String>>) restaurantMap;
        ArrayList<String> categories = new ArrayList<>();
        for (Map<String, String> categoriesMap : categoriesList) {
            categories.add(categoriesMap.get("title"));
        }
        return categories;
    }

    private static Map<String, Object> convertJsonToMap(String jsonString) {
        Gson gson = new Gson();
        TypeToken<Map<String, Object>> token = new TypeToken<>() {};
        return gson.fromJson(jsonString, token.getType());
    }
}
