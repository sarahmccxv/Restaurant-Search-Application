package api.response;

import entity.Restaurant;

public class SingleRestaurantResponse extends Response {

    public SingleRestaurantResponse(String response) {
        super(response);
    }

    public Restaurant getRestaurant() {
        return parser.parseSingleRestaurant(response);
    }
}