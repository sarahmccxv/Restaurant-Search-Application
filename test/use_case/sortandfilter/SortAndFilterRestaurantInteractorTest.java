package use_case.sortandfilter;

import api.Search.SearchCriteria;
import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;
import data_access.APIRestaurantDataAccessObject;
import entity.Restaurant;
import org.junit.jupiter.api.Test;
import use_case.sortandfilter.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SortAndFilterRestaurantInteractorTest {

    @Test
    void execute() {
        SortAndFilterRestaurantDataAccessInterface restaurantRespository = new APIRestaurantDataAccessObject();

        SortAndFilterRestaurantOutputBoundary successPresenter = new SortAndFilterRestaurantOutputBoundary() {
            @Override
            public void prepareSuccessView(SortAndFilterRestaurantOutputData resaturantInputData) {

                assertEquals("view restaurants", resaturantInputData.getPreviousView());

                SearchCriteria criteria = new SearchCriteria.Builder().build();
                criteria.setLocation("Toronto");
                criteria.setCategory("italy");
                criteria.setPriceLevel(SearchPriceLevel.MODERATE);
                criteria.setSortingMethod(SearchSortingMethods.RATING);
                assertEquals("italy", criteria.getCategory());
                assertEquals("Toronto", criteria.getLocation());
                assertEquals("rating", criteria.getSortingMethod());
                assertEquals("2", criteria.getPriceLevel());

                assertNotNull(resaturantInputData.getPreviousView());
                assertNotNull(resaturantInputData.getCriteria());
                assertNotNull(resaturantInputData.getRestaurants());
            }

            @Override
            public void prepareFailView(String error) {
                fail("No restaurant satisfies such filter and sort.");
            }
        };

        SearchCriteria criteria = new SearchCriteria.Builder().build();
        criteria.setLocation("Toronto");
        criteria.setCategory("Italy");
        criteria.setPriceLevel(SearchPriceLevel.MODERATE);
        criteria.setSortingMethod(SearchSortingMethods.RATING);
        SortAndFilterResaturantInputData sortAndFilterResaturantInputData = new SortAndFilterResaturantInputData(criteria, "view restaurants");
        SortAndFilterRestaurantInputBoundary interactor = new SortAndFilterRestaurantInteractor(restaurantRespository, successPresenter);
        interactor.execute(sortAndFilterResaturantInputData);
    }
}
