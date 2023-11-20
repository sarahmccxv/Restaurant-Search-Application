package data_access;

import api.yelp.YelpAPI;
import api.yelp.YelpApiServices;
import entity.*;
import use_case.view_favourites.ViewFavouritesDataAccessInterface;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileFavouritesDataAccessObject implements ViewFavouritesDataAccessInterface {

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, FavouritesList> favouritesMap = new HashMap<>();
    private RestaurantFactory restaurantFactory;

    public FileFavouritesDataAccessObject(String csvPath, RestaurantFactory restaurantFactory) throws IOException {
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
                    String favouritesIDList = String.valueOf(col[headers.get("favourites")]);
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

    @Override
    public boolean hasFavourites(String username){
        if (favouritesMap.get(username) == null) {
            return false;
        } else return !favouritesMap.get(username).getFavourites().isEmpty();
    }

    @Override
    public void saveFavourites(User user) {
        favouritesMap.put(user.getUsername(), user.getFavouritesList());
        this.save();
    }

    @Override
    public FavouritesList getFavouritesList(String username) {
        return favouritesMap.get(username);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(":", headers.keySet()));
            writer.newLine();
            for (String username : favouritesMap.keySet()) {
                String line = String.format("%s,%s",
                        username, favouritesMap.get(username).toString());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
