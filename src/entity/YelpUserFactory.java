package entity;

import java.time.LocalDateTime;

public class YelpUserFactory {

    public YelpUser create(String userID, String username) {
        return new YelpUser(userID, username);
    }

}
