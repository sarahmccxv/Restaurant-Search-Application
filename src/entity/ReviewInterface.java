package entity;

import java.time.LocalDateTime;

public interface ReviewInterface {

    int getReviewID();

    User getAuthor();

    Float getRating();

    String getContent();

    LocalDateTime getCreationTime();
}
