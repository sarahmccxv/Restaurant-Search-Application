package interface_adapter.sort_and_filter;


import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;
import entity.Restaurant;

import java.util.ArrayList;

public class SortAndFilterState {
    private SearchSortingMethods selectedSortingMethod;
    private SearchPriceLevel selectedPriceLevel;
    private String enteredCategory;

    public SortAndFilterState() {
        selectedSortingMethod = SearchSortingMethods.BEST_MATCH;
        selectedPriceLevel = SearchPriceLevel.CHEAP;
        enteredCategory = "";
    }

    public SearchSortingMethods getSelectedSortingMethod() {
        return selectedSortingMethod;
    }

    public void setSelectedSortingMethod(SearchSortingMethods selectedSortingMethod) {
        this.selectedSortingMethod = (selectedSortingMethod != null) ? selectedSortingMethod : SearchSortingMethods.BEST_MATCH;
    }

    public SearchPriceLevel getSelectedPriceLevel() {
        return selectedPriceLevel;
    }

    public void setSelectedPriceLevel(SearchPriceLevel selectedPriceLevel) {
        this.selectedPriceLevel = (selectedPriceLevel != null) ? selectedPriceLevel : SearchPriceLevel.CHEAP;
    }

    public String getEnteredCategory() {
        return enteredCategory;
    }

    public void setEnteredCategory(String enteredCategory) {
        this.enteredCategory = (enteredCategory != null) ? enteredCategory : "";
    }
}