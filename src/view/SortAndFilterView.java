package view;

import api.Search.SearchCriteria;
import api.Search.SearchPriceLevel;
import api.Search.SearchSortingMethods;
import entity.Restaurant;
import interface_adapter.ViewManagerModel;
import interface_adapter.restaurant.RestaurantController;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

import static api.Search.SearchPriceLevel.*;
import static api.Search.SearchSortingMethods.*;

public class SortAndFilterView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sort and filter restaurant";
    private final SortAndFilterViewModel sortAndFilterViewModel;
    final JTextField sortInputField = new JTextField(15);
    final JButton returnBack;
    final JComboBox<String> sortingMethodComboBox;
    final JComboBox<String> priceLevelComboBox;
    final JButton apply;
    private final SortAndFilterController sortAndFilterController;
    private ViewManagerModel viewManagerModel;
    private ViewRestaurantController viewRestaurantController;
    private RestaurantController restaurantController;
    private ViewRestaurantState viewRestaurantState;
    private ViewRestaurantViewModel viewRestaurantViewModel;

    public SortAndFilterView(SortAndFilterController sortAndFilterController,
                             SortAndFilterViewModel sortAndFilterViewModel,
                             ViewManagerModel viewManagerModel,
                             ViewRestaurantController viewRestaurantController,
                             RestaurantController restaurantController,
                             ViewRestaurantState viewRestaurantState,
                             ViewRestaurantViewModel viewRestaurantViewModel){
        this.sortAndFilterViewModel = sortAndFilterViewModel;
        this.sortAndFilterController = sortAndFilterController;
        this.viewManagerModel = viewManagerModel;
        this.viewRestaurantController = viewRestaurantController;
        this.restaurantController = restaurantController;
        this.viewRestaurantState = viewRestaurantState;
        this.viewRestaurantViewModel =viewRestaurantViewModel;

        sortAndFilterViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(SortAndFilterViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel applyButton = new JPanel();
        apply = new JButton(SortAndFilterViewModel.APPLY_BUTTON_LABEL);
        applyButton.add(apply);
        apply.addActionListener(this);

        SortAndFilterState state = sortAndFilterViewModel.getState();

        LabelTextPanel sort_restaurant = new LabelTextPanel(new JLabel("category"), sortInputField);

        sortInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        String text = sortInputField.getText() + e.getKeyChar();
                        state.setCategory(text);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        String[] sortingMethod = {BEST_MATCH.description, RATING.description, REVIEW_COUNT.description};
        sortingMethodComboBox = new JComboBox(sortingMethod);
        sortingMethodComboBox.setSelectedItem(state.getSearchSortingMethods().description);
        sortingMethodComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sortingMethodComboBox.getSelectedItem() == BEST_MATCH.description){
                    state.setSearchSortingMethods(BEST_MATCH);
                } else if (sortingMethodComboBox.getSelectedItem() == RATING.description) {
                    state.setSearchSortingMethods(RATING);
                } else if (sortingMethodComboBox.getSelectedItem() == REVIEW_COUNT.description) {
                    state.setSearchSortingMethods(REVIEW_COUNT);
                }
            }
        });

        String[] priceLevel = {CHEAP.range, MODERATE.range, PRICEY.range, LUXURY.range};
        priceLevelComboBox = new JComboBox<>(priceLevel);
        priceLevelComboBox.setSelectedItem(state.getSearchPriceLevel());
        priceLevelComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (priceLevelComboBox.getSelectedItem() == CHEAP.range){
                    state.setSearchPriceLevel(CHEAP);
                } else if (priceLevelComboBox.getSelectedItem() == MODERATE.range) {
                    state.setSearchPriceLevel(MODERATE);
                } else if (priceLevelComboBox.getSelectedItem() == PRICEY.range) {
                    state.setSearchPriceLevel(PRICEY);
                } else if (priceLevelComboBox.getSelectedItem() == LUXURY.range) {
                    state.setSearchPriceLevel(LUXURY);
                }

            }
        });

        apply.addActionListener(this);

//        apply.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                SearchCriteria criteria = state.getCriteria();
//                sortAndFilterController.execute(criteria, "view restaurants");
//                viewManagerModel.setActiveView("view restaurant");
//                System.out.println("in apply action");
//                viewManagerModel.firePropertyChanged();
//            }
//        });

        JPanel returnButton = new JPanel();
        returnBack = new JButton(SortAndFilterViewModel.RETURN_BUTTON_LABEL);
        returnButton.add(returnBack);
        returnBack.addActionListener(this);

        this.setLayout(new GridBagLayout());
        // GridBagConstraints for the title
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.gridwidth = 2; // Span 2 columns
        titleConstraints.anchor = GridBagConstraints.CENTER;
        this.add(title, titleConstraints);

        // GridBagConstraints for sorting method
        GridBagConstraints sortingMethodConstraints = new GridBagConstraints();
        sortingMethodConstraints.gridx = 0;
        sortingMethodConstraints.gridy = 1;
        sortingMethodConstraints.insets = new Insets(5, 5, 5, 5); // Add padding
        this.add(sortingMethodComboBox, sortingMethodConstraints);

        // GridBagConstraints for price level
        GridBagConstraints priceLevelConstraints = new GridBagConstraints();
        priceLevelConstraints.gridx = 1;
        priceLevelConstraints.gridy = 1;
        priceLevelConstraints.insets = new Insets(5, 5, 5, 5); // Add padding
        this.add(priceLevelComboBox, priceLevelConstraints);

        // GridBagConstraints for category
        GridBagConstraints categoryConstraints = new GridBagConstraints();
        categoryConstraints.gridx = 0;
        categoryConstraints.gridy = 2;
        categoryConstraints.insets = new Insets(5, 5, 5, 5); // Add padding
        this.add(sort_restaurant, categoryConstraints);

        // GridBagConstraints for apply button
        GridBagConstraints applyButtonConstraints = new GridBagConstraints();
        applyButtonConstraints.gridx = 0;
        applyButtonConstraints.gridy = 3;
        applyButtonConstraints.gridwidth = 2; // Span 2 columns
        this.add(applyButton, applyButtonConstraints);

        // GridBagConstraints for return button
        GridBagConstraints returnButtonConstraints = new GridBagConstraints();
        returnButtonConstraints.gridx = 1;
        returnButtonConstraints.gridy = 3;
        returnButtonConstraints.gridwidth = 2; // Span 2 columns
        this.add(returnButton, returnButtonConstraints);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Property change: " + evt.getPropertyName());
        SortAndFilterState sortAndFilterState = (SortAndFilterState) evt.getNewValue();
        // Remove existing action listeners
        for (ActionListener al : apply.getActionListeners()) {
            apply.removeActionListener(al);
        }
        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchCriteria criteria = sortAndFilterState.getCriteria();
                System.out.println("apply action");
                System.out.println(sortAndFilterState.getSearchSortingMethods());
                System.out.println(sortAndFilterState.getSearchPriceLevel());
                System.out.println(sortAndFilterState.getCategory());
                sortAndFilterController.execute(criteria, "view restaurants");
                viewRestaurantState.setRestaurants(sortAndFilterState.getRestaurants());
                viewRestaurantViewModel.setRestaurants(sortAndFilterState.getRestaurants());
//                viewRestaurantController.execute(sortAndFilterState.getUserID(), sortAndFilterState.getUsername(), sortAndFilterState.getPassword(), sortAndFilterState.getLocation());
                viewManagerModel.setActiveView("view restaurant");
                viewManagerModel.firePropertyChanged();
            }
        });
    }
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action performed: " + e.getActionCommand());
        SortAndFilterState state = sortAndFilterViewModel.getState();
        if (Objects.equals(e.getActionCommand(), "Return")) {
            viewManagerModel.setActiveView("view restaurant");
            System.out.println("return button clicked");
            viewManagerModel.firePropertyChanged();
    }
}
}
