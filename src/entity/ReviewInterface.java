package entity;

import java.time.LocalDateTime;

public interface ReviewInterface {

    String getReviewID();

    User getAuthor();

    Float getRating();

    String getContent();

    LocalDateTime getCreationTime();
}
