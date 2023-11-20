package use_case.sortandfilter;

import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;

import java.util.ArrayList;

public class SortAndFilterResaturantInputData {
    final private String name;
    final private String location;
    final private int limit;
    final private SearchSortingMethods sortingMethod;
    final private SearchPriceLevel priceLevel;
    final private String category;





    public SortAndFilterResaturantInputData(String name, String location, int limit, SearchSortingMethods sortingMethod, SearchPriceLevel priceLevel, String category){
        this.name = name;
        this.location = location;
        this.limit = limit;
        this.sortingMethod = sortingMethod;
        this.priceLevel = priceLevel;
        this.category = category;
    }

    String getName(){return name;}
    String getLocation() {
        return location;
    }
    int getLimit(){return limit;}
    SearchSortingMethods getSortingMethod(){return sortingMethod;}
    SearchPriceLevel getPriceLevel(){return priceLevel;}
    String getCategory(){return category;}
}
