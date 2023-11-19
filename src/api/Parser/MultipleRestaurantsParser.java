package api.Parser;

import entity.Restaurant;
import entity.RestaurantFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MultipleRestaurantsParser {

    public static ArrayList<Restaurant> parseFrom(JSONObject jsonObject) throws JSONException {
        ArrayList<Restaurant> restaurantsList = new ArrayList<>();
        RestaurantFactory restaurantFactory = new RestaurantFactory();
        JSONArray businessesArray = jsonObject.getJSONArray("businesses");

        for (int i = 0; i < businessesArray.length(); i++) {
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
    }
}