package use_case.sortandfilter;

import java.util.ArrayList;

public class SortAndFilterResaturantInputData {
    final private String location;
    final private int limit;
    final private String sortingMethod;
    final private ArrayList<String> category;





    public SortAndFilterResaturantInputData(String location, int limit, String sortingMethod, ArrayList<String> category){
        this.location = location;
        this.limit = limit;
        this.sortingMethod = sortingMethod;
        this.category = category;
    }

    String getLocation() {
        return location;
    }
    int getLimit(){return limit;}
    String getSortingMethod(){return sortingMethod;}
    ArrayList<String> getCategory(){return category;}

}
