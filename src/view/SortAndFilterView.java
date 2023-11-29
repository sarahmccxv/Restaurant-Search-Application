package view;

import api.Search.SearchCriteria;
import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;
import interface_adapter.ViewManagerModel;
import interface_adapter.sort_and_filter.SortAndFilterController;
import interface_adapter.sort_and_filter.SortAndFilterState;
import interface_adapter.sort_and_filter.SortAndFilterViewModel;
import interface_adapter.view_restaurants.ViewRestaurantController;
import interface_adapter.view_restaurants.ViewRestaurantState;
import interface_adapter.view_restaurants.ViewRestaurantViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static api.Search.SearchPriceLevel.*;
import static api.Search.SearchSortingMethods.*;

public class SortAndFilterView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sort and filter restaurant";
    private final SortAndFilterViewModel sortAndFilterViewModel;
    final JButton returnBack;
    final JComboBox<String> sortingMethodComboBox;
    final JComboBox<String> priceLevelComboBox;
    final JButton apply;
    private final SortAndFilterController sortAndFilterController;
    private ViewManagerModel viewManagerModel;

    public SortAndFilterView(SortAndFilterController sortAndFilterController,
                             SortAndFilterViewModel sortAndFilterViewModel,
                             ViewManagerModel viewManagerModel){
        this.sortAndFilterViewModel = sortAndFilterViewModel;
        this.sortAndFilterController = sortAndFilterController;
        this.viewManagerModel = viewManagerModel;

        sortAndFilterViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(SortAndFilterViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel applyButton = new JPanel();
        apply = new JButton(SortAndFilterViewModel.APPLY_BUTTON_LABEL);
        applyButton.add(apply);
        apply.addActionListener(this);

        String[] sortingMethod = {BEST_MATCH.toString(), RATING.toString(), REVIEW_COUNT.toString()};
        sortingMethodComboBox = new JComboBox(sortingMethod);
        sortingMethodComboBox.setSelectedItem(BEST_MATCH);
        sortingMethodComboBox.addActionListener(this);

        String[] priceLevel = {CHEAP.toString(), MODERATE.toString(), PRICEY.toString(), LUXURY.toString()};
        priceLevelComboBox = new JComboBox<>(priceLevel);
        priceLevelComboBox.setSelectedItem(CHEAP);
        priceLevelComboBox.addActionListener(this);

        JPanel returnButton = new JPanel();
        returnBack = new JButton(SortAndFilterViewModel.RETURN_BUTTON_LABEL);
        returnButton.add(returnBack);
        returnBack.addActionListener(this);


        this.add(title);
        this.add(sortingMethodComboBox);
        this.add(priceLevelComboBox);
        this.add(applyButton);
        this.add(returnButton);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SortAndFilterState sortAndFilterState = (SortAndFilterState) evt.getNewValue();
        if (evt.getSource() == sortingMethodComboBox) {
            sortAndFilterState.setSearchSortingMethods((SearchSortingMethods) sortingMethodComboBox.getSelectedItem());
        } else if (evt.getSource() == priceLevelComboBox) {
            sortAndFilterState.setSearchPriceLevel((SearchPriceLevel) priceLevelComboBox.getSelectedItem());
        }
        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchCriteria criteria = sortAndFilterState.getCriteria();
                String previousView = sortAndFilterState.getPreviousView();
                sortAndFilterController.execute(criteria, previousView);
                viewManagerModel.setActiveView("view restaurant");
            }
        });
    }
    public void actionPerformed(ActionEvent e) {
        System.out.println("return button clicked");
        SortAndFilterState state = sortAndFilterViewModel.getState();
//        if (state.getPreviousView().equals("view restaurants")) {
            viewManagerModel.setActiveView("view restaurant");
            viewManagerModel.firePropertyChanged();
    }
}
