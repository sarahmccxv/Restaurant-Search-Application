package use_case.search_restaurant;

public class SearchResaturantInputData {
    final private String restaurantName;

    public SearchResaturantInputData(String restaurantName){
        this.restaurantName = restaurantName;
    }

    String getRestaurantName() {
        return restaurantName;
    }

}
