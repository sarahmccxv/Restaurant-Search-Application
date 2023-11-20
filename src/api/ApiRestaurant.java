package api;

import entity.RestaurantFactory;
import entity.Restaurant;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.view_restaurant.ViewRestaurantDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.Math;

public class ApiRestaurant implements ApiRestaurantInterface, ViewRestaurantDataAccessInterface {
    private static final String API_URL = "https://api.yelp.com/v3/businesses/";
    private static final String API_TOKEN = "m50nmIojrs9_k4NDBc7TeGaSoPFtLXERQpG1o17SNWvp29XQbhSveJAzFwvodpyx2PCZX8yLA-37ULJKxE-Dxno0Hlpb1RfsnSk_3fWjEadWEjs9MPmpOQbhwHxMZXYx";
    private RestaurantFactory restaurantFactory;

    public ApiRestaurant() {
        this.restaurantFactory = new RestaurantFactory();
    }

    /**
     * Get a list of restaurants based on the location.
     *
     * @param locationName The name of the location
     * @param limit The number of restaurants
     * @return A list of restaurant objects with the specified location
     */
    public ArrayList<Restaurant> getLocalRestaurants(String locationName, int limit) throws JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("%ssearch?location=%s", API_URL, locationName))
                .addHeader("Authorization", String.format("Bearer %s", API_TOKEN))
                .build();
        try (Response response = client.newCall(request).execute()) {
            JSONObject responseBody = new JSONObject(response.body().string());
            if (response.code() == 200) {
                ArrayList<Restaurant> restaurantsList = new ArrayList<>();
                JSONArray businessesArray = responseBody.getJSONArray("businesses");

                for (int i = 0; i < Math.min(businessesArray.length(), limit); i++) {
                    String restaurantID = businessesArray.getJSONObject(i).getString("id");
                    String restaurantName = businessesArray.getJSONObject(i).getString("name");
                    String phoneNumber = businessesArray.getJSONObject(i).getString("phone");

                    JSONArray addressArray = businessesArray.getJSONObject(i).getJSONObject("location").getJSONArray("display_address");
                    String address = String.format("%s, %s", addressArray.getString(0), addressArray.getString(1));

                    ArrayList<String> categories = new ArrayList<>();
                    JSONArray categoriesArray = businessesArray.getJSONObject(i).getJSONArray("categories");
                    for (int j = 0; j < categoriesArray.length(); j++) {
                        categories.add(categoriesArray.getJSONObject(j).getString("alias"));
                    }

                    restaurantsList.add(restaurantFactory.create(restaurantID, restaurantName, address, phoneNumber, categories));
                }

                return restaurantsList;
            } else {
                throw new RuntimeException(responseBody.getJSONObject("error").getString("description"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get a list of 5 restaurants based on the location.
     *
     * @param locationName The name of the location
     * @return A list of restaurant objects with the specified location
     */
    public ArrayList<Restaurant> getLocalRestaurants(String locationName) {
        return getLocalRestaurants(locationName, 5);
    }

    /**
     * Get the restaurant by a unique ID
     *
     * @param restaurantID The ID to the restaurant
     * @return A restaurant object with the specified ID
     */
    public Restaurant getRestaurantByID(String restaurantID) throws JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("%s%s", API_URL, restaurantID))
                .addHeader("Authorization", String.format("Bearer %s", API_TOKEN))
                .build();
        try (Response response = client.newCall(request).execute()) {
            JSONObject responseBody = new JSONObject(response.body().string());
            if (response.code() == 200) {
                String restaurantName = responseBody.getString("name");
                String phoneNumber = responseBody.getString("phone");

                JSONArray addressArray = responseBody.getJSONObject("location").getJSONArray("display_address");
                String address = String.format("%s, %s", addressArray.getString(0), addressArray.getString(1));

                ArrayList<String> categories = new ArrayList<>();
                JSONArray categoriesArray = responseBody.getJSONArray("categories");
                for (int i = 0; i < categoriesArray.length(); i++) {
                    categories.add(categoriesArray.getJSONObject(i).getString("alias"));
                }

                return restaurantFactory.create(restaurantID, restaurantName, address, phoneNumber, categories);
            } else {
                throw new RuntimeException(responseBody.getJSONObject("error").getString("description"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the restaurant by phone number
     *
     * @param phoneNumber The phone number to the restaurant
     * @return A restaurant object with the specified phone number, or null if
     * the phone number isn't for any restaurants.
     */
    public Restaurant getRestaurantByPhoneNumber(String phoneNumber) throws JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("%ssearch/phone?phone=%s", API_URL, phoneNumber))
                .addHeader("Authorization", String.format("Bearer %s", API_TOKEN))
                .build();
        try (Response response = client.newCall(request).execute()) {
            JSONObject responseBody = new JSONObject(response.body().string());
            if (response.code() == 200) {
                JSONObject info = responseBody.getJSONArray("businesses").getJSONObject(0);

                String restaurantID = info.getString("id");
                String restaurantName = info.getString("name");

                JSONArray addressArray = info.getJSONObject("location").getJSONArray("display_address");
                String address = String.format("%s, %s", addressArray.getString(0), addressArray.getString(1));

                ArrayList<String> categories = new ArrayList<>();
                JSONArray categoriesArray = info.getJSONArray("categories");
                for (int i = 0; i < categoriesArray.length(); i++) {
                    categories.add(categoriesArray.getJSONObject(i).getString("alias"));
                }

                return restaurantFactory.create(restaurantID, restaurantName, address, phoneNumber, categories);
            } else {
                throw new RuntimeException(responseBody.getJSONObject("error").getString("description"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
