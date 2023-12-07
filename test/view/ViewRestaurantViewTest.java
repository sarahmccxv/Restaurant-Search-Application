package view;

import app.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.text.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

class ViewRestaurantViewTest {
    static String message = "";
    static boolean popUpDiscovered = false;


//    private JLabel getTitle() {
//        JFrame app = null;
//        Window[] windows = Window.getWindows();
//
//        for (Window window : windows) {
//            if (window instanceof JFrame) {
//                app = (JFrame) window;
//            }
//        }
//
//        assertNotNull(app);
//
//        Component root = app.getComponent(0);
//
//        Component cp = ((JRootPane) root).getContentPane();
//
//        JPanel jp = (JPanel) cp;
//
//        JPanel jp2 = (JPanel) jp.getComponent(0);
//
//        ViewRestaurantView sv = (ViewRestaurantView) jp2.getComponent(0);
//
//        JLabel title = (JLabel) sv.getComponent(0);
//
//        return title;
//    }
//    private JTextField getSearchTextField() {
//        JFrame app = null;
//        Window[] windows = Window.getWindows();
//
//        for (Window window : windows) {
//            if (window instanceof JFrame) {
//                app = (JFrame) window;
//            }
//        }
//
//        assertNotNull(app);
//
//        Component root = app.getComponent(0);
//
//        Component cp = ((JRootPane) root).getContentPane();
//
//        JPanel jp = (JPanel) cp;
//
//        JPanel jp2 = (JPanel) jp.getComponent(0);
//
//        ViewRestaurantView sv = (ViewRestaurantView) jp2.getComponent(0);
//
//        LabelTextPanel searchRestaurant = (LabelTextPanel) sv.getComponent(1);
//
//        JTextField searchText = (JTextField) searchRestaurant.getComponent(1);
//
//        return searchText;
//    }

    private JButton getSortButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();

        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);

        Component root = app.getComponent(0);
        JButton sortButton = (JButton) ((JRootPane) root).getContentPane().getComponent(0);
//        ViewRestaurantView viewRestaurantView = (ViewRestaurantView) mainPanel.getComponent(0);
//
//        JPanel searchButtonPanel = (JPanel) viewRestaurantView.getComponent(2);
//        JButton searchButton = (JButton) searchButtonPanel.getComponent(0);

        return sortButton;

//        Component root = app.getComponent(0);
//
//        Component cp = ((JRootPane) root).getContentPane();
//
//        JPanel jp = (JPanel) cp;
//
//        JButton jp2 = (JButton) jp.getComponent(0);

//        ViewRestaurantView sv = (ViewRestaurantView) jp2.getComponent(0);
//
//        JPanel searchButtonPanel = (JPanel) sv.getComponent(2);
//
//        JButton searchButton = (JButton) searchButtonPanel.getComponent(0);

    }

//    private JButton getSortButton() {
//        JFrame app = null;
//        Window[] windows = Window.getWindows();
//
//        for (Window window : windows) {
//            if (window instanceof JFrame) {
//                app = (JFrame) window;
//            }
//        }
//
//        assertNotNull(app);
//
//        Component root = app.getComponent(0);
//
//        Component cp = ((JRootPane) root).getContentPane();
//
//        JPanel jp = (JPanel) cp;
//
//        JPanel jp2 = (JPanel) jp.getComponent(0);
//
//        ViewRestaurantView sv = (ViewRestaurantView) jp2.getComponent(0);
//
//        JPanel sortButtonPanel = (JPanel) sv.getComponent(3);
//
//        JButton sortButton = (JButton) sortButtonPanel.getComponent(0);
//
//        return sortButton;
//    }

//    private JLabel getMessage() {
//        JFrame app = null;
//        Window[] windows = Window.getWindows();
//
//        for (Window window : windows) {
//            if (window instanceof JFrame) {
//                app = (JFrame) window;
//            }
//        }
//
//        assertNotNull(app);
//
//        Component root = app.getComponent(0);
//
//        Component cp = ((JRootPane) root).getContentPane();
//
//        JPanel jp = (JPanel) cp;
//
//        JPanel jp2 = (JPanel) jp.getComponent(0);
//
//        ViewRestaurantView sv = (ViewRestaurantView) jp2.getComponent(0);
//
//        JLabel message = (JLabel) sv.getComponent(4);
//
//        return message;
//    }
//    private JButton getReturnButton() {
//        JFrame app = null;
//        Window[] windows = Window.getWindows();
//
//        for (Window window : windows) {
//            if (window instanceof JFrame) {
//                app = (JFrame) window;
//            }
//        }
//
//        assertNotNull(app);
//
//        Component root = app.getComponent(0);
//
//        Component cp = ((JRootPane) root).getContentPane();
//
//        JPanel jp = (JPanel) cp;
//
//        JPanel jp2 = (JPanel) jp.getComponent(0);
//
//        ViewRestaurantView sv = (ViewRestaurantView) jp2.getComponent(0);
//
//        JPanel buttons = (JPanel) sv.getComponent(5);
//
//        JButton returnButton = (JButton) buttons.getComponent(0);
//
//        return returnButton;
//    }

//    @Test
//    public void titlePresent(){
//        JLabel title = getTitle();
//        assertEquals("View Restaurants", title.getText());
//        assert(popUpDiscovered);
//        assert(message.equals("Your note has been saved."));
//    }
//
//    @Test
//    public void searchTextFieldPresent(){
//        JTextField searchTextField = getSearchTextField();
//        searchTextField.setText("china");
//        assertEquals("china",searchTextField.getText());
//        assertFalse(popUpDiscovered);
//        assert(message.equals("Your note has been saved."));
//    }

    @Test
    public void sortButtonPresent() throws IOException {
        Main.main(null);
        JButton button = getSortButton();
        System.out.println(button.getText());
        assert(button.getText().equals("Sort and filter"));
    }

//    @Test
//    public void sortButtonPresent() {
//        JButton button = getSortButton();
//        assert(button.getText().equals("Sort and filter"));
//    }
//
//    @Test
//    public void messagePresent() {
//        JLabel message = getMessage();
//        assertEquals("Matched Results: ", message.getText());
//        assert(popUpDiscovered);
//        assert(message.equals("Your note has been saved."));
//    }
//
//    @Test
//    public void returnButtonPresent() {
//        JButton button = getReturnButton();
//        assert(button.getText().equals("Return"));
//    }

}