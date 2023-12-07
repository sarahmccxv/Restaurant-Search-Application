package data_access;

import api.yelp.YelpAPI;
import api.yelp.YelpApiServices;
import entity.*;
import use_case.add_to_favourites.AddToFavouritesDataAccessInterface;
import use_case.view_favourites.ViewFavouritesDataAccessInterface;
import use_case.remove_favourite.RemoveFavouriteDataAccessInterface;


import java.io.*;
import java.util.*;

public class FileFavouritesDataAccessObject implements ViewFavouritesDataAccessInterface,
        AddToFavouritesDataAccessInterface, RemoveFavouriteDataAccessInterface {

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, FavouritesList> favouritesMap = new HashMap<>();

    public FileFavouritesDataAccessObject(String csvPath) throws IOException {
        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("favourites", 1);
        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();
                assert header.equals("username:favourites");
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(":");
                    String username = col[headers.get("username")];
                    String favouritesIDList = col[headers.get("favourites")];
                    FavouritesList favouritesList = new FavouritesList();
                    for (String restaurantID : favouritesIDList.split(",")){
                        YelpApiServices yelpAPIServices = new YelpAPI();
                        Restaurant restaurant = yelpAPIServices.getRestaurantByID(restaurantID);
                        favouritesList.add(restaurant);
                    }
                    favouritesMap.put(username, favouritesList);
                }
            }
        }
    }

    public boolean hasFavourite(User user, Restaurant restaurant){
        String username = user.getUsername();
        if (hasFavourites(username)) {
            String restaurantID = restaurant.getRestaurantID();
            FavouritesList favouritesList = favouritesMap.get(username);
            for (Restaurant favourite : favouritesList) {
                if (favourite.getRestaurantID().equals(restaurantID)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasFavourites(String username){
        if (!favouritesMap.containsKey(username)) {
            return false;
        } else return !favouritesMap.get(username).isEmpty();
    }

    @Override
    public void saveFavourites(User user) {
        favouritesMap.put(user.getUsername(), user.getFavouritesList());
        this.save();
    }

    @Override
    public FavouritesList getFavouritesList(String username) {
        if (favouritesMap.containsKey(username)) {
            return favouritesMap.get(username);
        } else {
            return new FavouritesList();
        }
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(":", headers.keySet()));
            writer.newLine();
            for (String username : favouritesMap.keySet()) {
                String line = String.format("%s:%s",
                        username, favouritesMap.get(username).toString());
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeFavourite(String username, Restaurant restaurant){
        favouritesMap.get(username).remove(restaurant.getRestaurantID());
        if (favouritesMap.get(username).isEmpty()){
            favouritesMap.remove(username);
        }
        this.save();
    }

}
