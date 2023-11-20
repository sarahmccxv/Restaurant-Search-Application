package use_case.sortandfilter;

import entity.Restaurant;
import entity.Review;
import api.yelp.YelpApiServices;

import java.util.*;

public class SortAndFilterRestaurantInteractor implements SortAndFilterRestaurantInputBoundary {
    final SortAndFilterRestaurantDataAccessInterface sortAndFilterRestaurantDataAccessObject;
    final SortAndFilterRestaurantOutputBoundary sortAndFilterRestaurantPresenter;

    public SortAndFilterRestaurantInteractor(SortAndFilterRestaurantDataAccessInterface sortAndFilterRestaurantDataAccessInterface, SortAndFilterRestaurantOutputBoundary sortAndFilterRestaurantOutputBoundary){
        this.sortAndFilterRestaurantDataAccessObject = sortAndFilterRestaurantDataAccessInterface;
        this.sortAndFilterRestaurantPresenter = sortAndFilterRestaurantOutputBoundary;
    }
    @Override
    public void execute(SortAndFilterResaturantInputData sortAndFilterResaturantInputData) {
        String name = sortAndFilterResaturantInputData.getName();
        String location = sortAndFilterResaturantInputData.getLocation();
        int limit = sortAndFilterResaturantInputData.getLimit();
        SearchSortingMethods sortingMethod = sortAndFilterResaturantInputData.getSortingMethod();
        SearchPriceLevel priceLevel = sortAndFilterResaturantInputData.getPriceLevel();
        ArrayList<String> category = sortAndFilterResaturantInputData.getCategory();
        SearchCriteria criteria = new SearchCriteria.Builder()
                .setName(name)
                .setLocation(location)
                .setLimit(limit)
                .setSortingMethod(sortingMethod)
                .setPriceLevel(priceLevel)
                .setCategory(category)
                .build();
        ArrayList<Restaurant> localRestaurants = getLocalRestaurants(location);
        if (sortingMethod == SearchSortingMethods.BEST_MATCH) {
            int count = 0;
            Map<Restaurant,Integer> restaurantMap = new HashMap<>();
            for (Restaurant restaurant:localRestaurants){
                if(category.containsAll(restaurant.getCategories())){
                    count += 1;
                }
                restaurantMap.put(restaurant,count);
            }
            ArrayList<Restaurant> sorted = new ArrayList<>();
            for (Map.Entry<Restaurant, Integer> entry : restaurantMap.entrySet()) {
                while (sorted.size() < limit && entry.getKey()!= null){
                    if(entry.getValue() == 1){
                        sorted.add(entry.getKey());
                    }
                }
                if(sorted.size() < limit){
                    for(Map.Entry<Restaurant, Integer> entry_2 : restaurantMap.entrySet()) {
                        while (sorted.size() < limit){
                            if(entry.getValue() == 0){
                                sorted.add(entry_2.getKey());
                            }
                        }
                    }
                }
            }
            SortAndFilterRestaurantOutputData sortAndFilterRestaurantOutputData = new SortAndFilterRestaurantOutputData(sorted, false);
            sortAndFilterRestaurantPresenter.prepareSuccessView(sortAndFilterRestaurantOutputData);
        } else if (sortingMethod == SearchSortingMethods.RATING) {
            Map<Restaurant, Float> restaurantAndReviewMap = new HashMap<>();
            for (Restaurant restaurant : localRestaurants) {
                for (Review review : restaurant.getReviews())
                    restaurantAndReviewMap.put(restaurant, review.getRating());
            }
            List<Map.Entry<Restaurant, Float>> entryList = new ArrayList<>(restaurantAndReviewMap.entrySet());
            entryList.sort(Map.Entry.comparingByValue());
            ArrayList<Restaurant> sorted = new ArrayList<>();
            for (Map.Entry<Restaurant, Float> entry : entryList) {
                if (limit < entryList.size()){
                    while (sorted.size() < limit){
                        Restaurant restaurant = entry.getKey();
                        sorted.add(restaurant);
                    }
                }else {
                    Restaurant restaurant = entry.getKey();
                    sorted.add(restaurant);
                }
            }
            SortAndFilterRestaurantOutputData sortAndFilterRestaurantOutputData = new SortAndFilterRestaurantOutputData(sorted, false);
            sortAndFilterRestaurantPresenter.prepareSuccessView(sortAndFilterRestaurantOutputData);
        } else if (sortingMethod == SearchSortingMethods.REVIEW_COUNT) {
            localRestaurants.sort(Comparator.comparing(Restaurant::getReviewSize));
            ArrayList<Restaurant> sorted = new ArrayList<>();
            for (Restaurant restaurant : localRestaurants) {
                if (limit <= localRestaurants.size()) {
                    while (sorted.size() < limit) {
                        sorted.add(restaurant);
                    }
                } else {
                    sorted = localRestaurants;
                }
            }
            SortAndFilterRestaurantOutputData sortAndFilterRestaurantOutputData = new SortAndFilterRestaurantOutputData(sorted, false);
            sortAndFilterRestaurantPresenter.prepareSuccessView(sortAndFilterRestaurantOutputData);
        } else {
            sortAndFilterRestaurantPresenter.prepareFailView("No restaurant satisfies such condition.");


        }
    }
}
