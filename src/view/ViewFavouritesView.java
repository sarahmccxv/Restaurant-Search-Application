package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.view_favourites.ViewFavouritesViewModel;
import interface_adapter.view_favourites.ViewFavouritesState;

public class ViewFavouritesView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "view favourites";
    final JButton returnBack;
    final JPanel favourites;

    public ViewFavouritesView(ViewFavouritesViewModel viewFavouritesViewModel){
        viewFavouritesViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(ViewFavouritesViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        favourites = new JPanel();
        favourites.setLayout(new BoxLayout(favourites, BoxLayout.Y_AXIS));

        JPanel buttons = new JPanel();
        returnBack = new JButton(ViewFavouritesViewModel.RETURN_LABEL);
        buttons.add(returnBack);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(favourites);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ViewFavouritesState state = (ViewFavouritesState) evt.getNewValue();
        for (String favourite : state.getFavourites()) {
            favourites.add(new JLabel("<html>" + favourite.replace("\n", "<br>") + "</html>"));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}