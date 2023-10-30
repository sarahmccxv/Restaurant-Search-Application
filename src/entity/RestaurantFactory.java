package entity;
import java.util.ArrayList;

public class RestaurantFactory {
    public Restaurant create(int restaurantID, String restaurantName, String address,
                                    String phoneNumber,ArrayList<String> categories, ArrayList<Dish> menu){
        return new Restaurant(restaurantID, restaurantName, address, phoneNumber, categories, menu);
    }
}
