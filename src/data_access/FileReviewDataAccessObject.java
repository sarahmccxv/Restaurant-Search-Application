package data_access;

import entity.Review;
import entity.ReviewFactory;
import entity.User;
import use_case.register.RegisterUserDataAccessInterface;
import use_case.write_review.WriteReviewDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileReviewDataAccessObject implements WriteReviewDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Review> reviewsID = new LinkedHashMap<>();

    private RegisterUserDataAccessInterface fileUserDataAccessObject;
    private ReviewFactory reviewFactory;

    private Integer length = 1; // By default, the csv file should contain the row of column names

    public FileReviewDataAccessObject(String csvPath, ReviewFactory reviewFactory,
                                      RegisterUserDataAccessInterface fileUserDataAccessObject) throws IOException {
        this.reviewFactory = reviewFactory;
        this.fileUserDataAccessObject = fileUserDataAccessObject;

        csvFile = new File(csvPath);
        headers.put("reviewID", 0);
        headers.put("userID", 1);
        headers.put("restaurantID", 2);
        headers.put("rating", 3);
        headers.put("content", 4);
        // Add creation time?
        headers.put("creation_time", 5);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("reviewID,userID,restaurantID,rating,content,creation_time");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String reviewID = String.valueOf(col[headers.get("reviewID")]);
                    String userID = String.valueOf(col[headers.get("userID")]);
                    User user = fileUserDataAccessObject.getByUserID(userID);
                    String restaurantID = String.valueOf(col[headers.get("restaurantID")]);
                    Float rating = Float.valueOf(String.valueOf(col[headers.get("rating")]));
                    String content = String.valueOf(col[headers.get("content")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);

                    Review review = reviewFactory.create(reviewID, user, restaurantID, rating, content, ldt);
                    //System.out.println("user named: " + username + " is in the factory");
                    reviewsID.put(userID, review);
                    //System.out.println("user id: " + userID + " is in the factory");
                    length ++;
                }
            }
        }
    }

    @Override
    public void save(Review review) {
        reviewsID.put(review.getReviewID(), review);
        this.save();
    }

    @Override
    public Review getByReviewID(String reviewID) {
        return reviewsID.get(reviewID);
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Review review : reviewsID.values()) {
                String line = String.format("%s,%s,%s,%s,%s,%s",
                        review.getReviewID(), review.getAuthor().getUserID(), review.getRestaurantID(),
                        review.getRating().toString(), review.getContent(),
                        review.getCreationTime().toString());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existsByID(String identifier) {
        return reviewsID.containsKey(identifier);
    }

    @Override
    public void update() {
        //System.out.println("Length of current account in memory is " + length);
        //System.out.println("Length of csv file is " + countRows(csvFile));
//        if (length < countRows(csvFile)) {
//            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
//                String header = reader.readLine();
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    // Process the line
//                    String[] col = line.split(",");
//                    System.out.println("Reading csv file again to check updates");
//                    System.out.println("The user read has ID of " + col[headers.get("userID")]);
//                    String reviewID = String.valueOf(col[headers.get("reviewID")]);
//                    // Identify the new review and put into reviewIDs
//                    if (!reviewsID.containsKey(reviewID)) {
//                        String userID = String.valueOf(col[headers.get("userID")]);
//                        //System.out.println("New user named " + username + "found");
//                        User user = fileUserDataAccessObject.getByUserID(userID);
//                        String restaurantID = String.valueOf(col[headers.get("restaurantID")]);
//                        Float rating = Float.valueOf(String.valueOf(col[headers.get("rating")]));
//                        String content = String.valueOf(col[headers.get("content")]);
//                        String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
//                        LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
//
//                        Review review = reviewFactory.create(reviewID, user, restaurantID, rating, content, ldt);
//                        reviewsID.put(reviewID, review);
//                        System.out.println("review id: " + review.getReviewID() + " is in the factory");
//                        length++;
//                    }
//                }
//            }
//            catch (IOException e) {
//                throw new RuntimeException();
//            }
        }
        //System.out.println("After update, now accounts in memory have " + accounts.size() + " users");

    }


    public int countRows(File csvFile) {
        int rowCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            while (reader.readLine() != null) {
                rowCount++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rowCount;
    }
}
