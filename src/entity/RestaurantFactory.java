package entity;
import java.util.ArrayList;

public class RestaurantFactory {
    public Restaurant create(String restaurantID, String restaurantName, String address,
                                    String phoneNumber,ArrayList<String> categories){
        return new Restaurant(restaurantID, restaurantName, address, phoneNumber, categories);
    }
}
