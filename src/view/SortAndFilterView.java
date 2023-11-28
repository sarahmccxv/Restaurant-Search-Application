package view;

import api.Search.SearchCriteria;
import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;
import interface_adapter.sort_and_filter.SortAndFilterController;
import interface_adapter.sort_and_filter.SortAndFilterViewModel;
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
    public final String viewName = "filter and sort restaurants";
    private final SortAndFilterViewModel sortAndFilterViewModel;
    private final ViewRestaurantViewModel viewRestaurantViewModel;
    final JButton returnBack;
    final JPanel sorted;
    final JComboBox<String> sortingMethodComboBox;
    final JComboBox<String> priceLevelComboBox;
    final JTextField categoryInputField = new JTextField(15);
    final JButton apply;
    private SortAndFilterController sortAndFilterController;

    public SortAndFilterView(SortAndFilterController sortAndFilterController, SortAndFilterViewModel sortAndFilterViewModel, ViewRestaurantViewModel viewRestaurantViewModel){
        this.sortAndFilterViewModel = sortAndFilterViewModel;
        this.sortAndFilterController = sortAndFilterController;
        this.viewRestaurantViewModel = viewRestaurantViewModel;
        sortAndFilterViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(SortAndFilterViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        sorted = new JPanel();
        sorted.setLayout(new BoxLayout(sorted, BoxLayout.Y_AXIS));

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(sorted);

        JPanel applyButton = new JPanel();
        apply = new JButton(SortAndFilterViewModel.APPLY_BUTTON_LABEL);
        applyButton.add(apply);
        apply.addActionListener(this);

        String[] sortingMethod = {BEST_MATCH.toString(), RATING.toString(), REVIEW_COUNT.toString()};
        sortingMethodComboBox = new JComboBox(sortingMethod);
        sortingMethodComboBox.setSelectedItem(BEST_MATCH);

        String[] priceLevel = {CHEAP.toString(), MODERATE.toString(), PRICEY.toString(), LUXURY.toString()};
        priceLevelComboBox = new JComboBox<>(priceLevel);
        priceLevelComboBox.setSelectedItem(CHEAP);

        JPanel returnButton = new JPanel();
        returnBack = new JButton(SortAndFilterViewModel.RETURN_BUTTON_LABEL);
        returnButton.add(returnBack);
        returnBack.addActionListener(this);

        this.add(sortingMethodComboBox);
        this.add(priceLevelComboBox);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnBack) {
            System.out.println("Return button clicked");
            CardLayout cardLayout = (CardLayout) getParent().getLayout();
            cardLayout.show(getParent(), viewRestaurantViewModel.getViewName());
        } else if (e.getSource() == apply) {
            System.out.println("Apply button clicked");
            SearchSortingMethods selectedSortingMethod = (SearchSortingMethods) sortingMethodComboBox.getSelectedItem();
            SearchPriceLevel selectedPriceLevel = (SearchPriceLevel) priceLevelComboBox.getSelectedItem();
            String enteredCategory = categoryInputField.getText();

            System.out.println("Selected Sorting Method: " + selectedSortingMethod);
            System.out.println("Selected Price Level: " + selectedPriceLevel);
            System.out.println("Entered Category: " + enteredCategory);
            SearchCriteria criteria = new SearchCriteria.Builder()
                    .setCategory(enteredCategory)
                            .setSortingMethod(selectedSortingMethod)
                                    .setPriceLevel(selectedPriceLevel)
                                            .build();
            sortAndFilterController.execute(criteria);
        }
        }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if ("sortingMethod".equals(propertyName)) {
            SearchSortingMethods newSortingMethod = (SearchSortingMethods) evt.getNewValue();
            System.out.println("Sorting method changed to: " + newSortingMethod);
        } else if ("priceLevel".equals(propertyName)) {
            SearchPriceLevel newPriceLevel = (SearchPriceLevel) evt.getNewValue();
            System.out.println("Price level changed to: " + newPriceLevel);
        } else if ("categoryInput".equals(propertyName)) {
            String newCategoryInput = (String) evt.getNewValue();
            System.out.println("Category input changed to: " + newCategoryInput);
        }
    }
}
