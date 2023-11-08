package data_access;

import entity.*;
import use_case.view_favourites.ViewFavouritesDataAccessInterface;

import java.beans.FeatureDescriptor;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        headers.put("userID", 0);
        headers.put("favourites", 1);
        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();
                assert header.equals("userID:favourites");
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(":");
                    String userID = String.valueOf(col[headers.get("userID")]);
                    String favouritesIDList = String.valueOf(col[headers.get("favourites")]);
                    FavouritesList favouritesList = new FavouritesList();
                    for (String restaurantID : favouritesIDList.split(",")){
                        favouritesList.add(restaurantFactory.create(restaurantID));
                    }
                    favouritesMap.put(userID, favouritesList);
                }
            }
        }
    }

    @Override
    public void saveFavourites(User user) {
        favouritesMap.put(String.valueOf(user.getUserID()), user.getFavouritesList());
        this.save();
    }

    @Override
    public FavouritesList getFavouritesList(String userID) {
        return favouritesMap.get(userID);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(":", headers.keySet()));
            writer.newLine();
            for (String userID : favouritesMap.keySet()) {
                String line = String.format("%s,%s",
                        userID, favouritesMap.get(userID).toString());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
