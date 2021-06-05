package client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.chrono.JapaneseChronology;

public class guiClass {
    private JMenuBar mb;
    private JMenu home;


    public void runGUI() {
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
            adminUserFrame();
        });
        pane.add(btnPanel);
    }

    public JFrame adminUserFrame(){
        JFrame frame = new JFrame("Electronic Asset Trading Platform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        //frame.setResizable(false);
        home = new JMenu("Add User");
        mb = new JMenuBar();
        mb.add(home);
        frame.setJMenuBar(mb);
        frame.setVisible(true);

        adminHomePane(frame.getContentPane());
        return frame;
    }

    public void addAdminPane(Container pane){
        // Variable
        String[] labels = {"User ID: ","Organisation ID: ","Username: ", "First name: ", "Last name: " +
                "Password: ","Birthday: "};
    }
    public void adminHomePane(Container pane){

        BoxLayout box = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(box);

        // Create normal user panel
        JPanel normalPanel = new JPanel();
        JButton createNormal = new JButton("Create normal user");
        createNormal.setPreferredSize(new Dimension(150,25));
        normalPanel.add(createNormal);
        pane.add(normalPanel);

        // Create admin user panel
        JPanel adminPanel = new JPanel();
        JButton createAdmin = new JButton("Create admin user");
        createAdmin.setPreferredSize(new Dimension(150,25));
        adminPanel.add(createAdmin);
        pane.add(adminPanel);

        // Edit user panel
        JPanel editPanel = new JPanel();
        JButton editUser = new JButton("Edit user");
        editUser.setPreferredSize(new Dimension(150,25));
        editPanel.add(editUser);
        pane.add(editPanel);

        // Delete user panel
        JPanel deletePanel = new JPanel();
        JButton deleteUser = new JButton("Delete user");
        deleteUser.setPreferredSize(new Dimension(150,25));
        deletePanel.add(deleteUser);
        pane.add(deletePanel);
    }


}