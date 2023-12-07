package view;

import app.Main;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

class SortAndFilterViewTest {
    private JButton getSortBox() {
        JFrame app = null;
        Window[] windows = Window.getWindows();

        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app);

        Component root = app.getComponent(0);
        JButton sortBox = (JButton) ((JRootPane) root).getContentPane().getComponent(0);

        return sortBox;


    }
    @Test
    public void sortButtonPresent() throws IOException {
        Main.main(null);
//        JComboBox comboBox = getSortBox();
        JButton comboBox = getSortBox();
        System.out.println(comboBox.getText());
        assert(comboBox.getText().equals("Sort and filter"));
    }
}