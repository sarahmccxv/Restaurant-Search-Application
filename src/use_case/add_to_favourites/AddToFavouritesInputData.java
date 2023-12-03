package use_case.add_to_favourites;


import entity.Restaurant;

public class AddToFavouritesInputData {
    private final String username;
    private final Restaurant restaurant;

    public AddToFavouritesInputData(String username, Restaurant restaurant){
        this.username = username;
        this.restaurant = restaurant;
    }

    public String getUsername() {
        return username;
    }

    public Restaurant getRestaurant(){
        return restaurant;
    }
}
