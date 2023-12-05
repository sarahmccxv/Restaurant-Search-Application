package api.Parser;

import entity.Restaurant;
import entity.RestaurantFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SingleRestaurantParser {
     public static Restaurant parseFrom(JSONObject jsonObject) {
         try {
             RestaurantFactory restaurantFactory = new RestaurantFactory();

             String restaurantID = jsonObject.getString("id");
             String restaurantName = jsonObject.getString("name");
             String phoneNumber = jsonObject.getString("display_phone");
             String imageURL = jsonObject.getString("image_url");

             JSONArray addressArray = jsonObject.getJSONObject("location").getJSONArray("display_address");
             String address = String.format("%s, %s", addressArray.getString(0), addressArray.getString(1));

             ArrayList<String> categories = new ArrayList<>();
             JSONArray categoriesArray = jsonObject.getJSONArray("categories");
             for (int i = 0; i < categoriesArray.length(); i++) {
                 categories.add(categoriesArray.getJSONObject(i).getString("title"));
             }

             return restaurantFactory.create(restaurantID, restaurantName, address, phoneNumber, categories, imageURL);
         } catch (JSONException e) {
             throw new RuntimeException(e);
         }
     }
}