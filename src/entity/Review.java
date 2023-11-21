package entity;
import java.time.LocalDateTime;

public class Review implements ReviewInterface {
    private final String reviewID;
    private final User author;
    private Float rating;
    private String content;
    private LocalDateTime creationTime;

    public Review(String reviewID, User author, Float rating,
                  String content, LocalDateTime creationTime) {
        this.reviewID = reviewID;
        this.author = author;
        this.rating = rating;
        this.content = content;
        this.creationTime = creationTime;
    }

    @Override
    public String getReviewID() {
        return reviewID;
    }

    @Override
    public User getAuthor() {
        return author;
    }

    @Override
    public Float getRating() {
        return rating;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    private void changeContent(String newContent) {
        this.content = newContent;
    }

    private void changeRating(Float newRating) {
        this.rating = newRating;
    }

    @Override
    public String toString() {
        return reviewID + "\n User: " + author.getUserID() +
        ", " + author.getUsername() +
                "\n Rating: " + rating +
                "\n CreationTime: " + creationTime;
    }
}
