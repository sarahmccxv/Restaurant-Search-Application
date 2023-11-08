package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import interface_adapter.view_favourites.ViewFavouritesController;
import interface_adapter.view_favourites.ViewFavouritesViewModel;
import interface_adapter.view_favourites.ViewFavouritesState;

public class ViewFavouritesView extends JPanel{
    public static String viewName = "view favourites";
    private final JButton viewFavourites;
    private final ViewFavouritesController viewFavouritesController;
    private final ViewFavouritesViewModel viewFavouritesViewModel;

    public ViewFavouritesView(ViewFavouritesController viewFavouritesController, ViewFavouritesViewModel
            viewFavouritesViewModel){
        this.viewFavouritesController = viewFavouritesController;
        this.viewFavouritesViewModel = viewFavouritesViewModel;

        JLabel title = new JLabel(ViewFavouritesViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        viewFavourites = new JButton(ViewFavouritesViewModel.VIEW_FAVOURITES_BUTTON_LABEL);
        buttons.add(viewFavourites);

        viewFavourites.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(viewFavourites)) {
                            ViewFavouritesState currentState = viewFavouritesViewModel.getState();
                            viewFavouritesController.execute(currentState.getUserID());
                        }
                    }
                }
        );
    }

}