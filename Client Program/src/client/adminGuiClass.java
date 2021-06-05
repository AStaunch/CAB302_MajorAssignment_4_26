package client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class adminGuiClass {
    private JMenuBar mb;
    private JMenu home;

    public adminGuiClass(){
        adminUserFrame();
    }
    AdminControls a = new AdminControls();

    private JFrame adminUserFrame(){
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

        adminHomePane(frame.getContentPane(), frame);
        return frame;
    }

    private Frame addUser(JFrame mainFrame){
        // Variables
        JLabel label;
        JPanel orgPanel;
        Integer columnSize = 15;

        JFrame frame = new JFrame("Add Admin User");
        frame.setSize(300, 275);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        EnabledOnClose(frame, mainFrame);

        Container pane = frame.getContentPane();
        BoxLayout box = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(box);


        // Panel for the org ID
        String[] orgNames = a.listOrg();
        orgPanel = new JPanel();
        label = new JLabel("Org Name: ");
        JComboBox orgNameList = new JComboBox(orgNames);
        orgPanel.add(label);
        orgPanel.add(orgNameList);

        // Panel for user name
        JPanel userNamePanel = new JPanel();
        label = new JLabel("Username: ");
        JTextField userName = new JTextField("", columnSize);
        userNamePanel.add(label);
        userNamePanel.add(userName);


        // Panel for first name
        JPanel firstNamePanel = new JPanel();
        label = new JLabel("First name: ");
        JTextField firstName = new JTextField("", columnSize);
        firstNamePanel.add(label);
        firstNamePanel.add(firstName);


        // Panel for last name
        JPanel lastNamePanel = new JPanel();
        label = new JLabel("Last name: ");
        JTextField lastName = new JTextField("", columnSize);
        lastNamePanel.add(label);
        lastNamePanel.add(lastName);


        // Panel for password
        JPanel pwdPanel = new JPanel();
        label = new JLabel("Password: ");
        JPasswordField pwd = new JPasswordField("", columnSize);
        pwdPanel.add(label);
        pwdPanel.add(pwd);

        // Panel for isAdmin
        JPanel isAdminPanel = new JPanel();
        JCheckBox isAdminCheckBox = new JCheckBox("Admin");
        isAdminCheckBox.addActionListener(e ->{
            if(isAdminCheckBox.isSelected()){
                orgPanel.setVisible(false);
            } else {
                orgPanel.setVisible(true);
            }
        });
        isAdminCheckBox.setBounds(100,100,50,50);
        isAdminPanel.add(isAdminCheckBox);

        // Button panel
        JPanel panel = new JPanel();

        // Add button
        JButton add = new JButton("Add");
        add.addActionListener(e ->{
            String orgName = (String) orgNameList.getSelectedItem();
            Integer orgID = a.getOrg(orgName).getID(); // Replace this with a query
            String password = new String(pwd.getPassword());
            if (isAdminCheckBox.isSelected()){
                normalUser newUser = new normalUser(orgID, userName.getText(),
                        firstName.getText(), lastName.getText(), password, true);
                a.addUser(newUser);
            } else {
                normalUser newUser = new normalUser(orgID, userName.getText(),
                        firstName.getText(), lastName.getText(), password, false);
                a.addUser(newUser);
            }
        });

        // Clear button
        JButton clear = new JButton("Clear");
        clear.addActionListener(e ->{
            userName.setText("");
            firstName.setText("");
            lastName.setText("");
            pwd.setText("");
            isAdminCheckBox.setSelected(false);
        });

        panel.add(add);
        panel.add(clear);

        // Add all panel to the container
        pane.add(isAdminPanel);
        pane.add(orgPanel);
        pane.add(userNamePanel);
        pane.add(firstNamePanel);
        pane.add(lastNamePanel);
        pane.add(pwdPanel);
        pane.add(panel);

        frame.add(panel);
        frame.setVisible(true);

        return frame;
    }

    private void adminHomePane(Container pane, JFrame mainFrame){

        BoxLayout box = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(box);

        // Create admin user panel
        JPanel addUserPanel = new JPanel();
        JButton createUser = new JButton("Create user");
        createUser.addActionListener(e ->{
            addUser(mainFrame);
        });
        createUser.setPreferredSize(new Dimension(150,25));
        addUserPanel.add(createUser);
        pane.add(addUserPanel);

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

    private void EnabledOnClose(JFrame currentFrame,JFrame previousFrame){
        previousFrame.setEnabled(false);

        currentFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                previousFrame.setEnabled(true);
            };
        });
    }
}
