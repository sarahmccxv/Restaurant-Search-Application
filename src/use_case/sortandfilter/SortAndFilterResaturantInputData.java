package use_case.sortandfilter;

import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;

import java.util.ArrayList;

public class SortAndFilterResaturantInputData {
    final private SearchSortingMethods sortingMethod;
    final private SearchPriceLevel priceLevel;
    final private String category;


    public SortAndFilterResaturantInputData(SearchSortingMethods sortingMethod, SearchPriceLevel priceLevel, String category){
        this.sortingMethod = sortingMethod;
        this.priceLevel = priceLevel;
        this.category = category;
    }
    SearchSortingMethods getSortingMethod(){return sortingMethod;}
    SearchPriceLevel getPriceLevel(){return priceLevel;}
    String getCategory(){return category;}
}
