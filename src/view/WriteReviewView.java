package view;

import interface_adapter.add_review.AddReviewController;
import interface_adapter.add_review.AddReviewState;
import interface_adapter.add_review.AddReviewViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.restaurant.RestaurantController;
import interface_adapter.view_restaurants.ViewRestaurantState;
import interface_adapter.write_review.WriteReviewController;
import interface_adapter.write_review.WriteReviewState;
import interface_adapter.write_review.WriteReviewViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WriteReviewView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Add Review";
    private final AddReviewViewModel addReviewViewModel;
    private final WriteReviewViewModel writeReviewViewModel;
    private RestaurantController restaurantController;

    final JTextField ratingInputField = new JTextField(5);
    final JTextField contentInputField = new JTextField(50);
    private final JLabel ratingErrorField = new JLabel();

    final JButton save;
    final JButton returnBack;

    private final AddReviewController addReviewController;
    //private final WriteReviewController writeReviewController;

    public WriteReviewView(AddReviewViewModel addReviewViewModel,
                           WriteReviewViewModel writeReviewViewModel,
                           AddReviewController addReviewController,
                           //WriteReviewController writeReviewcontroller,
                           RestaurantController restaurantController) {

        //this.writeReviewController = writeReviewcontroller;
        this.addReviewViewModel = addReviewViewModel;
        this.addReviewController = addReviewController;
        this.restaurantController = restaurantController;
        this.writeReviewViewModel = writeReviewViewModel;
        this.writeReviewViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Write Your Review");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel ratingInfo = new LabelTextPanel(
                new JLabel("Enter rating: "), ratingInputField);
        LabelTextPanel contentInfo = new LabelTextPanel(
                new JLabel("Enter content: "), contentInputField);

        JPanel buttons = new JPanel();
        save = new JButton(writeReviewViewModel.SAVE_BUTTON_LABEL);
        buttons.add(save);
        returnBack = new JButton(writeReviewViewModel.RETURN_BUTTON_LABEL);
        buttons.add(returnBack);

        save.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(save)) {
                            System.out.println("Save clicked");
                            WriteReviewState currentState = writeReviewViewModel.getState();
                            //writeReviewController.execute(
                                    //currentState.getUser(),
                                    //currentState.getRestaurant());
                        }
                    }
                }
        );

        returnBack.addActionListener(this);

        ratingInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                WriteReviewState currentState = writeReviewViewModel.getState();
                currentState.setRating(ratingInputField.getText() + e.getKeyChar());
                writeReviewViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        contentInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        WriteReviewState currentState = writeReviewViewModel.getState();
                        currentState.setContent(contentInputField.getText() + e.getKeyChar());
                        writeReviewViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.add(title);
        this.add(ratingInfo);
        this.add(ratingErrorField);
        this.add(contentInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
        AddReviewState state = addReviewViewModel.getState();
        String userID = state.getUser().getUserID();
        String username = state.getUser().getUsername();
        String password = state.getUser().getPassword();
        String restaurantID = state.getRestaurant().getRestaurantID();
        String previous_view = state.getPrevious_view();
        restaurantController.execute(userID, username, password, restaurantID, previous_view);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Property changed at write review");
    }

    //private void setFields(WriteReviewState state) {
        //usernameInputField.setText(state.getUsername());
    //}
}
