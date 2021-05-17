package client;

import javax.swing.*;
import java.awt.*;

public class guiClass {
    public void runGUI() {
        JFrame frame = new JFrame("Electronic Asset Trading Platform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    /*
    private JPanel initPane() {
        JPanel detailsPane = new JPanel();
        detailsPane.setLayout(new BoxLayout(detailsPane, ));

        return;
    }
    */
}