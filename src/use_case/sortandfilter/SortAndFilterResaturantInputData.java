package use_case.sortandfilter;

import api.Search.SearchCriteria;
import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;

import java.util.ArrayList;

public class SortAndFilterResaturantInputData {
    SearchCriteria criteria;


    public SortAndFilterResaturantInputData(SearchCriteria criteria){
        this.criteria = criteria;
    }
    SearchCriteria getCriteria(){return criteria;}
}
