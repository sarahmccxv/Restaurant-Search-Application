package entity;

import java.time.LocalDateTime;

public interface ReviewInterface {

    String getReviewID();

    CommonUser getAuthor();

    String getRestaurantID();

    Float getRating();

    String getContent();

    LocalDateTime getCreationTime();
}
