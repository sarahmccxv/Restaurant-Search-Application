package entity;
import java.time.LocalDateTime;

public class Review extends CommonReview implements ReviewInterface {
    private final User author;
    private Float rating;
    private String content;

    public Review(String reviewID, User author, Restaurant restaurant, Float rating,
                  String content, LocalDateTime creationTime) {
        super(reviewID, author, restaurant, rating, content, creationTime);
        this.author = author;
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

    private void setContent(String newContent) {
        this.content = newContent;
    }

    private void setRating(Float newRating) {
        this.rating = newRating;
    }

    @Override
    public String toString() {
        return super.getReviewID() + "\n User: " + author.getUserID() +
        ", " + author.getUsername() +
                "\n Rating: " + rating +
                "\n CreationTime: " + super.getCreationTime();
    }
}
