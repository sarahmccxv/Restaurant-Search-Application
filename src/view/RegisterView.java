package view;

import interface_adapter.register.RegisterController;
import interface_adapter.register.RegisterState;
import interface_adapter.register.RegisterViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RegisterView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";

    private final RegisterViewModel registerViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);

    private final JTextField locationInputField = new JTextField(15);
    private final RegisterController registerController;

    private final JButton register;
    private final JButton cancel;

    public RegisterView(RegisterController controller, RegisterViewModel registerViewModel) {

        this.registerController = controller;
        this.registerViewModel = registerViewModel;
        registerViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(RegisterViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(RegisterViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(RegisterViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(RegisterViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
        LabelTextPanel locationInfo = new LabelTextPanel(
                new JLabel(RegisterViewModel.LOCATION_LABEL), locationInputField);

        JPanel buttons = new JPanel();
        register = new JButton(RegisterViewModel.REGISTER_BUTTON_LABEL);
        register.setOpaque(true);
        register.setBorderPainted(false);
        register.setBackground(Color.BLUE); // for the background
        register.setForeground(Color.WHITE); // for the text
        buttons.add(register);
        cancel = new JButton(RegisterViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

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
                        }
                    }
                }
        );

        cancel.addActionListener(this);

        // This makes a new KeyListener implementing class, instantiates it, and
        // makes it listen to keystrokes in the usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!
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