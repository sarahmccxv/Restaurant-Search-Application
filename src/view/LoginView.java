package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;
    final JButton register;
    private final LoginController loginController;
    private final ViewManagerModel viewManagerModel;

    public LoginView(LoginViewModel loginViewModel, LoginController controller, ViewManagerModel viewManagerModel) {

        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Login Screen");
        Font titleFont = new Font("Arial", Font.BOLD, 13); // Change "Arial" to the desired font family
        title.setFont(titleFont);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        JFrame frame = new JFrame("Login buttons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttons = new JPanel();

        register = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(register);

        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        logIn.setBackground(Color.BLUE); // for the background
        logIn.setForeground(Color.WHITE); // for the text
        frame.setContentPane(buttons);
        frame.pack();
        frame.setVisible(false);
        buttons.add(logIn);



        logIn.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();
                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        register.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(register)) {
                            viewManagerModel.setActiveView("sign up");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                loginViewModel.setState(currentState);
                usernameInfo.getLabel().setForeground(Color.GRAY);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        usernameInputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Set text color to black when focused
                usernameInfo.getLabel().setForeground(Color.GRAY);
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
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
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

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
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
        LoginState state = (LoginState) evt.getNewValue();
        if (this.loginViewModel.getState().getLogout()){
            usernameInputField.setText("");
            passwordInputField.setText("");
        } else {
        setFields(state);}
    }

    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }

}