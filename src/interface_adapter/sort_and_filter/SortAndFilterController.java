package interface_adapter.sort_and_filter;

import api.Search.SearchCriteria;
import use_case.sortandfilter.SortAndFilterResaturantInputData;
import use_case.sortandfilter.SortAndFilterRestaurantInputBoundary;

public class SortAndFilterController {
    final SortAndFilterRestaurantInputBoundary sortAndFilterRestaurantInteractor;

    public SortAndFilterController(SortAndFilterRestaurantInputBoundary sortAndFilterRestaurantInteractor) {
        this.sortAndFilterRestaurantInteractor = sortAndFilterRestaurantInteractor;
    }

    public void execute(SearchCriteria criteria, String previousView) {
        SortAndFilterResaturantInputData sortAndFilterRestaurantInputData = new SortAndFilterResaturantInputData(criteria, previousView);
        sortAndFilterRestaurantInteractor.execute(sortAndFilterRestaurantInputData);
    }
}