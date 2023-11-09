package data_access;

import entity.*;
import use_case.register.RegisterUserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class FileUserDataAccessObject implements RegisterUserDataAccessInterface {

    private final File userFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<Integer, User> accounts = new HashMap<>();

    private final Map<Integer, ArrayList<Restaurant>> restaurants = new HashMap<>();

    private UserFactory userFactory;
    private RestaurantFactory restaurantFactory;

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory, RestaurantFactory restaurantFactory) throws IOException {
        this.userFactory = userFactory;
        this.restaurantFactory = restaurantFactory;

        userFile = new File(csvPath);
        headers.put("userID", 0);
        headers.put("username", 1);
        headers.put("password", 2);
        headers.put("location", 3);
        headers.put("reviews", 5);
        // The following is for favouritesList
        headers.put("restaurantID", 6);
        headers.put("restaurantName", 7);
        headers.put("address", 8);
        headers.put("phoneNumber", 9);
        headers.put("categories", 10);
        headers.put("menu", 11);

        if (userFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
                String header = reader.readLine();

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");

                    int userID = Integer.parseInt(String.valueOf(col[headers.get("userID")]));
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String location = String.valueOf(col[headers.get("location")]);
                    String favourites = String.valueOf(col[headers.get("favourites")]);
                    String reviews = String.valueOf(col[headers.get("reviews")]);
                    String[] restaurantIDs = String.valueOf(col[headers.get("restaurantID")]).split(",");
                    String[] restaurantNames = String.valueOf(col[headers.get("restaurantName")]).split(",");
                    String[] addresses = String.valueOf(col[headers.get("address")]).split(",");
                    String[] phoneNumbers = String.valueOf(col[headers.get("phoneNumber")]).split(",");

                    // For categories and menus, the data format stored in csv file is like food1,food2,food3|food1,food2,food3 .......
                    String[] categories = String.valueOf(col[headers.get("categories")]).split("\\|");
                    String[] menus = String.valueOf(col[headers.get("menu")]).split("\\|");

                    // Create list of restaurants corresponding to the user
                    ArrayList<Restaurant> restaurants = new ArrayList<>();
                    for (int i = 0; i < restaurantIDs.length; i++) {
                        List<String> categoryList = Arrays.asList(categories[i].split(","));
                        ArrayList<String> menuForEachRestaurant = new ArrayList<>(categoryList);
                        restaurantFactory.create(restaurantIDs[i],
                                                 restaurantNames[i],
                                                 addresses[i],
                                                 phoneNumbers[i],
                                                 menuForEachRestaurant);
                    }

                    this.restaurants.put(userID, restaurants);


                    User user = this.userFactory.create(userID, username, password, location);
                    accounts.put(userID, user);
                }
            }
        }
    }

    @Override
    public void save(User user) {
        accounts.put(user.getUserID(), user);
        this.save();
    }

//    @Override
    public User get(int userID) {
        return accounts.get(userID);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(userFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = String.format("%s,%s,%s,%s",
                        user.getUserID(), user.getUsername(), user.getPassword(), user.getLocation());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByID(int identifier) {
        return accounts.containsKey(identifier);
    }
}
