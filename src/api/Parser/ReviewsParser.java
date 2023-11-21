package api.Parser;

import entity.Review;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReviewsParser {
//    public static ArrayList<Review> parseFrom(JSONObject jsonObject) {
//         try {
//             CommonUserFactory restaurantFactory = new RestaurantFactory();
//
//             String restaurantID = jsonObject.getString("id");
//             String restaurantName = jsonObject.getString("name");
//             String phoneNumber = jsonObject.getString("phone");
//
//             JSONArray addressArray = jsonObject.getJSONObject("location").getJSONArray("display_address");
//             String address = String.format("%s, %s", addressArray.getString(0), addressArray.getString(1));
//
//             ArrayList<String> categories = new ArrayList<>();
//             JSONArray categoriesArray = jsonObject.getJSONArray("categories");
//             for (int i = 0; i < categoriesArray.length(); i++) {
//                 categories.add(categoriesArray.getJSONObject(i).getString("alias"));
//             }
//
//             return restaurantFactory.create(restaurantID, restaurantName, address, phoneNumber, categories);
//         } catch (JSONException e) {
//             throw new JSONException(e);
//         }
//     }
}