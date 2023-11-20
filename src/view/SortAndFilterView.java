package view;

import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;
import interface_adapter.login.LoginState;
import interface_adapter.sort_and_filter.SortAndFilterController;
import interface_adapter.sort_and_filter.SortAndFilterState;
import interface_adapter.sort_and_filter.SortAndFilterViewModel;
import interface_adapter.view_favourites.ViewFavouritesState;
import interface_adapter.view_favourites.ViewFavouritesViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SortAndFilterView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "filter and sort restaurants";
    private final SortAndFilterViewModel sortAndFilterViewModel;
    final JButton returnBack;
    final JPanel sorted;
    final JComboBox sortingMethods;
    final JComboBox priceLevel;
    final JTextField categoryInputField = new JTextField(15);
    final JButton search;
    private SortAndFilterController sortAndFilterController;

    public SortAndFilterView(SortAndFilterController sortAndFilterController, SortAndFilterViewModel sortAndFilterViewModel){
        this.sortAndFilterViewModel = sortAndFilterViewModel;
        this.sortAndFilterController = sortAndFilterController;
        sortAndFilterViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(SortAndFilterViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        sorted = new JPanel();
        sorted.setLayout(new BoxLayout(sorted, BoxLayout.Y_AXIS));

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(sorted);

        LabelTextPanel categoryInfo = new LabelTextPanel(
                new JLabel(SortAndFilterViewModel.CATEGORY_LABEL), categoryInputField);

        JPanel returnButton = new JPanel();
        returnBack = new JButton(SortAndFilterViewModel.RETURN_BUTTON_LABEL);
        returnButton.add(returnBack);
        returnBack.addActionListener(this);

        JPanel searchButton = new JPanel();
        search = new JButton(SortAndFilterViewModel.SEARCH_BUTTON_LABEL);
        searchButton.add(search);
        search.addActionListener(this);

        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new BoxLayout(comboBoxPanel, BoxLayout.Y_AXIS));
        sortingMethods = new JComboBox<>(SearchSortingMethods.values());
        sortingMethods.setSelectedItem(SearchSortingMethods.BEST_MATCH);
        priceLevel = new JComboBox<>(SearchPriceLevel.values());
        sortingMethods.setSelectedItem(SearchPriceLevel.CHEAP);
        comboBoxPanel.add(sortingMethods);
        comboBoxPanel.add(priceLevel);

        sorted.add(comboBoxPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnBack) {
            // Handle the returnBack button click
            System.out.println("Return button clicked");
        } else if (e.getSource() == search) {
            // Handle the search button click
            System.out.println("Search button clicked");
            SearchSortingMethods selectedSortingMethod = (SearchSortingMethods) sortingMethods.getSelectedItem();
            SearchPriceLevel selectedPriceLevel = (SearchPriceLevel) priceLevel.getSelectedItem();
            String enteredCategory = categoryInputField.getText();

            // Perform actions based on the selected options
            System.out.println("Selected Sorting Method: " + selectedSortingMethod);
            System.out.println("Selected Price Level: " + selectedPriceLevel);
            System.out.println("Entered Category: " + enteredCategory);
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
