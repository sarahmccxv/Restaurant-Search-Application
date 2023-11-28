package view;

import javax.swing.*;

public class DoubleLabelPanel extends JPanel {
    DoubleLabelPanel(JLabel label1, JLabel label2) {
        this.add(label1);
        this.add(label2);
    }
}