package entity;

import java.util.ArrayList;

public class ReviewList {

    private ArrayList<Review> reviewlist = new ArrayList<>();

    public ReviewList(){
        this.reviewlist = new ArrayList<Review>();
    }

    public void add(Review review){
        reviewlist.add(review);
    }

    public ArrayList<Review> getReviewlist(){
        return reviewlist;
    }

    public void remove(Review review){
        reviewlist.remove(review);
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (Review review : reviewlist){
            result.append(review.toString()).append(",");
        }
        result.delete(result.length() - 1, result.length());
        return result.toString();
    }
}

