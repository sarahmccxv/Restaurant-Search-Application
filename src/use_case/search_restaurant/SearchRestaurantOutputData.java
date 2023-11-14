package use_case.search_restaurant;

public class SearchRestaurantOutputData {
    private final String restaurantName;
    private boolean useCaseFailed;

    public SearchRestaurantOutputData(String restaurantName, boolean useCaseFailed){
        this.restaurantName = restaurantName;
        this.useCaseFailed = useCaseFailed;
    }
    public String getRestaurantName(){
        return restaurantName;
    }
}
