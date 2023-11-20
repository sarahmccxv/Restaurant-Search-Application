package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonUser {
    private final String username;
    //private final ArrayList<Review> reviewsList = new ArrayList<>();

    public CommonUser(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
