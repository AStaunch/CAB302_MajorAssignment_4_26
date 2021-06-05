package client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class guiClass {
    private JMenuBar mb;
    private JMenu home;

    public guiClass(){
        initFrame();
    }

    public JFrame initFrame(){
        JFrame frame = new JFrame("Electronic Asset Trading Platform");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        LoginPane(frame.getContentPane(), frame);
        frame.setVisible(true);
        return frame;
    }

    public void LoginPane(Container pane, JFrame frame){
        int columnSize = 20;
        BoxLayout box = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(box);

        FlowLayout flow = new FlowLayout();
        JPanel userNamePanel = new JPanel();
        userNamePanel.setLayout(flow);
        userNamePanel.setBorder(new EmptyBorder(30,0,0,0));

        JLabel userNameLabel = new JLabel();
        userNameLabel.setText("Username: ");
        userNamePanel.add(userNameLabel);

        JTextField userNameTextField = new JTextField("", columnSize);
        userNamePanel.add(userNameTextField);

        pane.add(userNamePanel);

        JPanel pwdPanel = new JPanel();
        pwdPanel.setLayout(flow);

        JLabel pwdLabel = new JLabel();
        pwdLabel.setText("Password: ");
        pwdPanel.add(pwdLabel);

        JPasswordField pwdTextField = new JPasswordField("", columnSize);
        pwdPanel.add(pwdTextField);

        pane.add(pwdPanel);

        JPanel btnPanel = new JPanel();

        JButton logIn = new JButton();
        logIn.setText("Login");
        btnPanel.add(logIn);
        logIn.addActionListener(e -> {
            frame.dispose();
            adminGuiClass admin = new adminGuiClass();
        });
        pane.add(btnPanel);

        JButton client = new JButton();
        client.setText("Login");
        btnPanel.add(client);
        client.addActionListener(e -> {
            frame.dispose();
            userGuiClass admin = new userGuiClass();
        });
        pane.add(btnPanel);
    }




}