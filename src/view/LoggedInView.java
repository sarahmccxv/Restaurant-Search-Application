package view;

import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutViewModel;
import interface_adapter.view_favourites.ViewFavouritesController;
import interface_adapter.view_favourites.ViewFavouritesState;
import interface_adapter.view_favourites.ViewFavouritesViewModel;
import interface_adapter.view_restaurants.ViewRestaurantController;
import interface_adapter.view_restaurants.ViewRestaurantViewModel;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfileViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    private final ViewFavouritesViewModel viewFavouritesViewModel;
    private final ViewFavouritesController viewFavouritesController;
    private final ViewRestaurantViewModel viewRestaurantViewModel;
    private final ViewRestaurantController viewRestaurantController;
    private final LogoutViewModel logoutViewModel;
    private final LogoutController logoutController;
    private final UserProfileViewModel userProfileViewModel;
    private final UserProfileController userProfileController;


    JLabel username;
    JLabel userImageLabel;

    final JButton logOut;
    final JButton viewFavourites;
    final JButton viewRestaurant;
    final JButton userProfile;

    /**
     * A window with a title and a JButton.
     */
    public LoggedInView(LoggedInViewModel loggedInViewModel,
                        ViewFavouritesViewModel viewFavouritesViewModel,
                        LogoutViewModel logoutViewModel,
                        ViewFavouritesController viewFavouritesController,
                        ViewRestaurantViewModel viewRestaurantViewModel,
                        ViewRestaurantController viewRestaurantController,
                        UserProfileViewModel userProfileViewModel,
                        UserProfileController userProfileController,
                        LogoutController logoutController) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.logoutViewModel = logoutViewModel;
        this.viewFavouritesViewModel = viewFavouritesViewModel;
        this.viewFavouritesController = viewFavouritesController;
        this.viewRestaurantViewModel = viewRestaurantViewModel;
        this.viewRestaurantController = viewRestaurantController;
        this.userProfileViewModel = userProfileViewModel;
        this.userProfileController = userProfileController;
        this.logoutController = logoutController;

        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        //Set font
        Font titleFont = new Font("Arial", Font.BOLD, 13); // Change "Arial" to the desired font family
        title.setFont(titleFont);

        // Add an empty border to create space above and below the title
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));


        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.X_AXIS));

        JLabel usernameInfo = new JLabel("Currently logged in as: ");
//        usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        username = new JLabel();
        userImageLabel = new JLabel();
        userInfoPanel.add(usernameInfo);
        userInfoPanel.add(username);
        userInfoPanel.add(userImageLabel);

        // Add an empty border to create space above and below the user information panel
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JPanel buttons = new JPanel();
        viewRestaurant = new JButton(loggedInViewModel.VIEW_RESTAURANT_BUTTON_LABEL);
        Font viewFond = new Font("Arial", Font.BOLD, 12); // Change "Arial" to the desired font family
        viewRestaurant.setFont(viewFond);
        buttons.add(viewRestaurant);

        viewFavourites = new JButton(loggedInViewModel.VIEW_FAVOURITES_BUTTON_LABEL);
        Font favFont = new Font("Arial", Font.BOLD, 12); // Change "Arial" to the desired font family
        viewFavourites.setFont(favFont);
        buttons.add(viewFavourites);

        userProfile = new JButton(loggedInViewModel.USER_PROFILE_BUTTON_LABEL);
        Font userFont = new Font("Arial", Font.BOLD, 12); // Change "Arial" to the desired font family
        userProfile.setFont(userFont);
        buttons.add(userProfile);

        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        Font logoutFont = new Font("Arial", Font.BOLD, 12); // Change "Arial" to the desired font family
        logOut.setFont(logoutFont);
        buttons.add(logOut);

        logOut.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {
                            String username = loggedInViewModel.getState().getUsername();
                            logoutController.execute(username);
                            String message = logoutViewModel.getState().getMessage();
                            JOptionPane.showMessageDialog(null, message);
                        }
                    }
                }
        );

        viewFavourites.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(viewFavourites)) {
                            String username = loggedInViewModel.getState().getUsername();
                            viewFavouritesController.execute(username);
                        }
                    }
                }
        );

        viewRestaurant.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(viewRestaurant)) {
                            String userID = loggedInViewModel.getState().getUserID();
                            String username = loggedInViewModel.getState().getUsername();
                            String password = loggedInViewModel.getState().getPassword();
                            String location = loggedInViewModel.getState().getLocation();
                            //System.out.println("This is LoggedInView. My state has username " + username + " and password " + password);
                            viewRestaurantController.execute(userID, username, password, location);
                        }
                    }
                }
        );

        userProfile.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(userProfile)) {
                            String userID = loggedInViewModel.getState().getUserID();
                            String username = loggedInViewModel.getState().getUsername();
                            String password = loggedInViewModel.getState().getPassword();
                            String location = loggedInViewModel.getState().getLocation();
                            userProfileController.execute(userID, username, password, location);
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
//        this.add(usernameInfo);
        this.add(userInfoPanel);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        username.setText(state.getUsername());
        String imagePath = loggedInViewModel.getImagePath();

        // Update the user's image label
        if (imagePath != null && !imagePath.isEmpty()) {
            System.out.println(imagePath);
            ImageIcon icon = new ImageIcon(imagePath);
            icon = new ImageIcon(icon.getImage().getScaledInstance(10, 10, Image.SCALE_DEFAULT));
            userImageLabel.setIcon(icon);
        }
    }
}