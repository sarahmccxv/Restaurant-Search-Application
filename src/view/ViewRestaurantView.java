package view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.stream.Collectors;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.view_favourites.ViewFavouritesViewModel;
import interface_adapter.view_favourites.ViewFavouritesState;

public class ViewFavouritesView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "view favourites";
    final JButton returnBack;
    final JPanel favourites;
    private ViewFavouritesViewModel viewFavouritesViewModel;
    private LoginController loginController;

    public ViewFavouritesView(ViewFavouritesViewModel viewFavouritesViewModel,
                              LoginController loginController){
        this.viewFavouritesViewModel = viewFavouritesViewModel;
        this.loginController = loginController;

        viewFavouritesViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel(ViewFavouritesViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        favourites = new JPanel();
        favourites.setLayout(new BoxLayout(favourites, BoxLayout.Y_AXIS));

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(favourites);

        JPanel buttons = new JPanel();
        returnBack = new JButton(ViewFavouritesViewModel.RETURN_LABEL);
        buttons.add(returnBack);
        returnBack.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(centerPanel);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ViewFavouritesState state = (ViewFavouritesState) evt.getNewValue();
        favourites.removeAll();
        favourites.revalidate();
        favourites.repaint();
        if (!state.getNoFavouritesMessage().isEmpty()){
            favourites.add(new JLabel(state.getNoFavouritesMessage()));
        } else {
            for (String favourite : state.getFavourites()) {
                String text = "<html><b>" + favourite.split("\n")[0] + "</b><br>" +
                        favourite.split("\n")[1] + "<br>" + favourite.split("\n")[2] + "</html>";
                favourites.add(new JLabel(text));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ViewFavouritesState state = viewFavouritesViewModel.getState();
        loginController.execute(state.getUsername(), state.getPassword());
    }
}