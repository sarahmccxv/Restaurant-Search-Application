package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfilePresenter;
import interface_adapter.user_profile.UserProfileState;
import interface_adapter.user_profile.UserProfileViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UserProfileView extends JPanel implements ActionListener, PropertyChangeListener {
// Declare the components
    public final String viewName = "user profile";
    private final ViewManagerModel viewManagerModel;
    private final UserProfileViewModel userProfileViewModel;
    private final UserProfileController userProfileController;
    private JTextField passwordInputField = new JTextField(15);
    private JTextField locationInputField = new JTextField(15);
    JLabel userID, username;
    private JButton editButton, saveButton, cancelButton, returnButton;

    private String tempPassword, tempLocation;

    private final LoginController loginController;

    // Constructor to set up the GUI
    public UserProfileView(ViewManagerModel viewManagerModel, UserProfileViewModel userProfileViewModel, UserProfileController userProfileController, LoginController loginController) {
        this.userProfileViewModel = userProfileViewModel;
        this.userProfileController = userProfileController;
        userProfileViewModel.addPropertyChangeListener(this);

        this.viewManagerModel = viewManagerModel;
        this.loginController = loginController;

        JLabel title = new JLabel(userProfileViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        userID = new JLabel();
        DoubleLabelPanel userIDInfo = new DoubleLabelPanel(
                new JLabel(userProfileViewModel.USERID_LABEL), userID);
        username = new JLabel();
        DoubleLabelPanel usernameInfo = new DoubleLabelPanel(
                new JLabel(userProfileViewModel.USERNAME_LABEL), username);
        passwordInputField.setEditable(false);
        passwordInputField.setCaretColor(Color.WHITE);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(userProfileViewModel.PASSWORD_LABEL), passwordInputField);
        locationInputField.setEditable(false);
        locationInputField.setCaretColor(Color.WHITE);
        LabelTextPanel locationInfo = new LabelTextPanel(
                new JLabel(userProfileViewModel.LOCATION_LABEL), locationInputField);

        JPanel buttons = new JPanel();
        editButton = new JButton(userProfileViewModel.EDIT_BUTTON_LABEL);
        buttons.add(editButton);
        editButton.setVisible(true);
        saveButton = new JButton(userProfileViewModel.SAVE_BUTTON_LABEL);
        buttons.add(saveButton);
        saveButton.setVisible(false);
        cancelButton = new JButton(userProfileViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancelButton);
        cancelButton.setVisible(false);
        returnButton = new JButton(userProfileViewModel.RETURN_BUTTON_LABEL);
        buttons.add(returnButton);
        returnButton.setVisible(true);

        returnButton.addActionListener(this);

        editButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Enable editing and show save and cancel buttons
                        passwordInputField.setEditable(true);
                        passwordInputField.setCaretColor(Color.BLACK);
                        locationInputField.setEditable(true);
                        locationInputField.setCaretColor(Color.BLACK);
                        editButton.setVisible(false);
                        saveButton.setVisible(true);
                        cancelButton.setVisible(true);
                        returnButton.setVisible(false);
                    }
                }
        );

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                tempPassword = passwordInputField.getText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                tempPassword = passwordInputField.getText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        locationInputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                tempLocation = locationInputField.getText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                tempLocation = locationInputField.getText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        saveButton.addActionListener(this);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save the changes and disable editing
                UserProfileState currentState = userProfileViewModel.getState();
                currentState.setPassword(tempPassword);
                currentState.setLocation(tempLocation);
                userProfileViewModel.setState(currentState);
                passwordInputField.setEditable(false);
                passwordInputField.setCaretColor(Color.WHITE);
                locationInputField.setEditable(false);
                locationInputField.setCaretColor(Color.WHITE);
                editButton.setVisible(true);
                saveButton.setVisible(false);
                cancelButton.setVisible(false);
                returnButton.setVisible(true);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Discard the changes and disable editing
                userProfileViewModel.setLastState();
                UserProfileState userProfileState = userProfileViewModel.getState();
                passwordInputField.setText(userProfileState.getPassword());
                locationInputField.setText(userProfileState.getLocation());
                passwordInputField.setCaretColor(Color.WHITE);
                passwordInputField.setEditable(false);
                locationInputField.setCaretColor(Color.WHITE);
                locationInputField.setEditable(false);
                editButton.setVisible(true);
                saveButton.setVisible(false);
                cancelButton.setVisible(false);
                returnButton.setVisible(true);
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(userIDInfo);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(locationInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(returnButton)) {
            UserProfileState state = userProfileViewModel.getState();
            loginController.execute(state.getUsername(), state.getPassword());
        } else if (evt.getSource().equals(saveButton)) {
            UserProfileState state = userProfileViewModel.getState();
            userProfileController.execute(state.getUserID(), state.getUsername(), state.getPassword(), state.getLocation());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        UserProfileState state = (UserProfileState) evt.getNewValue();
        userID.setText(state.getUserID().toString());
        username.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
        locationInputField.setText(state.getLocation());
    }
}
