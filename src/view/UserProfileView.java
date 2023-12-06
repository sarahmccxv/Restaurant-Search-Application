package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfileState;
import interface_adapter.user_profile.UserProfileViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UserProfileView extends JPanel implements ActionListener, PropertyChangeListener {
// Declare the components
    public final String viewName = "user profile";
    private final ViewManagerModel viewManagerModel;
    private final UserProfileViewModel userProfileViewModel;
    private final UserProfileController userProfileController;
    public JTextField passwordInputField = new JTextField(15);
    public JTextField locationInputField = new JTextField(15);
    private JLabel userID, username;
    public JButton editButton, saveButton, cancelButton, returnButton;
    private String tempPassword, tempLocation;
    private final LoginController loginController;
    private JLabel avatarLabel;
    private ImageIcon defaultAvatar;
    private int avatarSize = 100;

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

        // Inside the constructor
        avatarLabel = new JLabel();
        avatarLabel.setPreferredSize(new Dimension(avatarSize, avatarSize)); // Set default size

        // Load the default avatar image (assuming you have a default avatar image file)
        try {
            defaultAvatar = new ImageIcon("user-fill.png"); // Replace with your default image path
            // Scale the default avatar image to fit the label
            defaultAvatar = new ImageIcon(defaultAvatar.getImage().getScaledInstance(avatarSize, avatarSize, Image.SCALE_DEFAULT));
            avatarLabel.setIcon(defaultAvatar);
        } catch (Exception ex) {
            ex.printStackTrace();
            // Handle exception if the default avatar image fails to load
        }

        avatarLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    java.io.File selectedFile = fileChooser.getSelectedFile();

                    ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());
                    icon = new ImageIcon(icon.getImage().getScaledInstance(avatarSize, avatarSize, Image.SCALE_DEFAULT));
                    avatarLabel.setIcon(icon);
                }
            }
        });
        avatarLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.setLayout(new BorderLayout()); // Use BorderLayout for overall structure

        JPanel userInfoPanel = new JPanel(); // Panel for user info and avatar
        userInfoPanel.setLayout(new GridLayout(1, 2, 5, 0)); // GridLayout for user info and avatar side by side

        // Left side: User info
        JPanel infoLabelsPanel = new JPanel();
        infoLabelsPanel.setLayout(new BoxLayout(infoLabelsPanel, BoxLayout.Y_AXIS));
        infoLabelsPanel.add(userIDInfo);
        infoLabelsPanel.add(usernameInfo);
        infoLabelsPanel.add(passwordInfo);
        infoLabelsPanel.add(locationInfo);

        userInfoPanel.add(infoLabelsPanel);

        // Right side: Avatar
        JPanel avatarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        avatarPanel.add(avatarLabel);

        userInfoPanel.add(avatarPanel);

        // Center panel for user profile label
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(title);

        // Main panel layout setup
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(userInfoPanel, BorderLayout.SOUTH);

        // Add mainPanel to the main panel (UserProfileView)
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
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