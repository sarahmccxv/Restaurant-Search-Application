package use_case.sortandfilter;

import api.Search.SearchCriteria;
import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;

import java.util.ArrayList;

public class SortAndFilterResaturantInputData {
    SearchCriteria criteria;
    private String previousView;


    public SortAndFilterResaturantInputData(SearchCriteria criteria, String previousView){
        this.criteria = criteria;
        this.previousView = previousView;
    }
    SearchCriteria getCriteria(){return criteria;}
    public String getPreviousView() {return previousView;}
}
