package api.response;

import entity.Restaurant;

import java.util.ArrayList;

public class MultipleRestaurantResponse extends Response {
    public MultipleRestaurantResponse(String response) {
        super(response);
    }

    public ArrayList<Restaurant> getRestaurants() {
        return parser.parseMultipleRestaurants(response);
    }
}