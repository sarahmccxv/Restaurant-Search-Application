package entity;

import java.time.LocalDateTime;

public interface ReviewInterface {

    String getReviewID();

    CommonUser getAuthor();

    Restaurant getRestaurant();

    Float getRating();

    String getContent();

    LocalDateTime getCreationTime();
}
