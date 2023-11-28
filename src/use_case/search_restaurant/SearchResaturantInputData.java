package use_case.search_restaurant;

public class SearchResaturantInputData {
    final private String restaurantName;
    final private String location;

    public SearchResaturantInputData(String location, String restaurantName){
        this.location = location;
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }
    String getLocation(){
        return location;
    }

}
