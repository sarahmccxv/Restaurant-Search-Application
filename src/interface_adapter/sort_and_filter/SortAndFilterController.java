package interface_adapter.sort_and_filter;

import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;
import use_case.sortandfilter.SortAndFilterResaturantInputData;
import use_case.sortandfilter.SortAndFilterRestaurantInputBoundary;

public class SortAndFilterController {
    final SortAndFilterRestaurantInputBoundary sortAndFilterRestaurantInteractor;

    public SortAndFilterController(SortAndFilterRestaurantInputBoundary sortAndFilterRestaurantInteractor) {
        this.sortAndFilterRestaurantInteractor = sortAndFilterRestaurantInteractor;
    }

    public void execute(SearchSortingMethods sortingMethods, SearchPriceLevel priceLevel, String category) {
        SortAndFilterResaturantInputData sortAndFilterRestaurantInputData = new SortAndFilterResaturantInputData(sortingMethods, priceLevel, category);
        sortAndFilterRestaurantInteractor.execute(sortAndFilterRestaurantInputData);
    }
}