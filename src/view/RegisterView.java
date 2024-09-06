package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.register.RegisterController;
import interface_adapter.register.RegisterState;
import interface_adapter.register.RegisterViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RegisterView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";

    private final RegisterViewModel registerViewModel;
    public final JTextField usernameInputField = new JTextField(15);
    public final JPasswordField passwordInputField = new JPasswordField(15);
    public final JPasswordField repeatPasswordInputField = new JPasswordField(15);

    public final JTextField locationInputField = new JTextField(15);
    private final RegisterController registerController;
    private final ViewManagerModel viewManagerModel;

    public final JButton register;
    public final JButton login;

    public RegisterView(RegisterController registerController, RegisterViewModel registerViewModel,
                        ViewManagerModel viewManagerModel) {

        this.registerController = registerController;
        this.registerViewModel = registerViewModel;
        this.viewManagerModel = viewManagerModel;

        registerViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(RegisterViewModel.TITLE_LABEL);
        Font titleFont = new Font("Arial", Font.BOLD, 13); // Change "Arial" to the desired font family
        title.setFont(titleFont);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(RegisterViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(RegisterViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(RegisterViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
        LabelTextPanel locationInfo = new LabelTextPanel(
                new JLabel(RegisterViewModel.LOCATION_LABEL), locationInputField);

        JFrame frame = new JFrame("Register buttons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));

        login = new JButton(RegisterViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(login);

        register = new JButton(RegisterViewModel.REGISTER_BUTTON_LABEL);
        register.setBackground(Color.BLUE); // for the background
        register.setForeground(Color.WHITE); // for the text
        frame.setContentPane(buttons);
        frame.pack();
        frame.setVisible(false);
        buttons.add(register);


        register.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(register)) {
                            RegisterState currentState = registerViewModel.getState();

                            registerController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    currentState.getRepeatPassword(),
                                    currentState.getLocation()
                            );
                            // Clear the text fields after registration
                            usernameInputField.setText("");
                            passwordInputField.setText("");
                            repeatPasswordInputField.setText("");
                            locationInputField.setText("");
                        }
                    }
                }
        );

        login.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(login)) {
                            viewManagerModel.setActiveView("log in");
                            //System.out.println("Active view is now " + viewManagerModel.getActiveView());
                            viewManagerModel.firePropertyChanged();
                            usernameInputField.setText("");
                            passwordInputField.setText("");
                            repeatPasswordInputField.setText("");
                            locationInputField.setText("");
                        }
                    }
                }
        );

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        RegisterState currentState = registerViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        registerViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        usernameInputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Set text color to black when focused
                usernameInfo.getLabel().setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Set text color to gray when not focused
                usernameInfo.getLabel().setForeground(Color.GRAY);
            }
        });
        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        RegisterState currentState = registerViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        registerViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        passwordInputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Set text color to black when focused
                passwordInfo.getLabel().setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Set text color to gray when not focused
                passwordInfo.getLabel().setForeground(Color.GRAY);
            }
        });
        passwordInputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Set text color to black when focused
                passwordInfo.getLabel().setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Set text color to gray when not focused
                passwordInfo.getLabel().setForeground(Color.GRAY);
            }
        });

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        RegisterState currentState = registerViewModel.getState();
                        currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                        registerViewModel.setState(currentState); // Hmm, is this necessary?
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        repeatPasswordInputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Set text color to black when focused
                repeatPasswordInfo.getLabel().setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Set text color to gray when not focused
                repeatPasswordInfo.getLabel().setForeground(Color.GRAY);
            }
        });
        repeatPasswordInputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Set text color to black when focused
                repeatPasswordInfo.getLabel().setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Set text color to gray when not focused
                repeatPasswordInfo.getLabel().setForeground(Color.GRAY);
            }
        });

        locationInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        RegisterState currentState = registerViewModel.getState();
                        String text = locationInputField.getText() + e.getKeyChar();
                        currentState.setLocation(text);
                        registerViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        locationInputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Set text color to black when focused
                locationInfo.getLabel().setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Set text color to gray when not focused
                locationInfo.getLabel().setForeground(Color.GRAY);
            }
        });
        locationInputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Set text color to black when focused
                locationInfo.getLabel().setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Set text color to gray when not focused
                locationInfo.getLabel().setForeground(Color.GRAY);
            }
        });


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(locationInfo);
        this.add(buttons);
    }


    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        RegisterState state = (RegisterState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }
}