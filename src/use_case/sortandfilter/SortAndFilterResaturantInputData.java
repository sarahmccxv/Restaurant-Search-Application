package use_case.sortandfilter;

import java.util.ArrayList;

public class SortAndFilterResaturantInputData {
    final private String name;
    final private String location;
    final private int limit;
    final private SearchSortingMethods sortingMethod;
    final private SearchPriceLevel priceLevel;
    final private ArrayList<String> category;





    public SortAndFilterResaturantInputData(String name, String location, int limit, SearchSortingMethods sortingMethod, SearchPriceLevel priceLevel, ArrayList<String> category){
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
    ArrayList<String> getCategory(){return category;}
}
