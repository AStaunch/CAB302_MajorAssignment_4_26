package client;
import common.InventoryAsset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

/**
 * Admin GUI Class for Admin window functionalities
 */

public class adminGuiClass {
    private JMenuBar mb;
    private JMenuItem home, orgPage, unitPage;
    private JFrame frame;
    private JLabel label;
    AdminControls a = new AdminControls();
    UserControls u = new UserControls();

    /**
     * Runnable for the class
     */
    public adminGuiClass(){
        adminUserFrame();
    }

    /**
     * Create the main frame for the admin user
     * @return JFrame - Initial screen
     */
    private JFrame adminUserFrame(){
        frame = new JFrame("Electronic Asset Trading Platform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        //frame.setResizable(false);
        adminHomePane(frame.getContentPane(), frame);
        home = new JMenuItem("User management");
        orgPage = new JMenuItem("Org management");
        unitPage = new JMenuItem("Unit management");

        // Listeners for the menu bars
        home.addActionListener(e->{
            frame.getContentPane().removeAll();
            adminHomePane(frame.getContentPane(), frame);
            frame.validate();
            frame.repaint();
        });
        orgPage.addActionListener(e->{
            frame.getContentPane().removeAll();
            adminOrgPane(frame.getContentPane(), frame);
            frame.validate();
            frame.repaint();
        });
        unitPage.addActionListener(e->{
            frame.getContentPane().removeAll();
            adminAssetPane(frame.getContentPane(), frame);
            frame.validate();
            frame.repaint();
        });
        mb = new JMenuBar();
        mb.add(home);
        mb.add(orgPage);
        mb.add(unitPage);
        frame.setJMenuBar(mb);
        frame.setVisible(true);
        return frame;
    }

    /**
     * Create frame for adding user
     * @param mainFrame frame from the initial screen
     * @return Frame - which has the section the admin user needs to fill to create a new user
     */
    private JFrame addUser(JFrame mainFrame){
        // Variables
        JLabel label;
        JPanel orgPanel;
        Integer columnSize = 15;

        // Create a frame
        frame = new JFrame("Add User");
        frame.setSize(300, 275);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Disable the previous frame
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
        add.addActionListener(e -> {
            String orgName = (String) orgNameList.getSelectedItem();
            Integer orgID = a.getOrg(orgName).getID();
            String uName = userName.getText();
            String fName = firstName.getText();
            String lName = lastName.getText();
            String password = a.encode("String.valueOf(pwd.getPassword())");
            // Checker for inputs
            if (uName.isEmpty() || fName.isEmpty() || lName.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Enter a value for each section!!!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (a.getUser(uName) instanceof normalUser){
                JOptionPane.showMessageDialog(frame, "Username already used!!!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
                userName.setText("");
            }else {
                if (isAdminCheckBox.isSelected()){
                    normalUser newUser = new normalUser(1, uName,
                            fName, lName, password, true);
                    a.addUser(newUser);
                } else {
                    normalUser newUser = new normalUser(orgID, uName,
                            fName, lName, password, false);
                    a.addUser(newUser);
                }
                JOptionPane.showMessageDialog(frame, "Successfully added new user");
                userName.setText("");
                firstName.setText("");
                lastName.setText("");
                pwd.setText("");
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

    /**
     * Creates a pane which contains the buttons for each functionalities
     * @param pane pane from the main frame
     * @param mainFrame frame the initial screen
     */
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
        editUser.addActionListener(e ->{
            editUserFrame(mainFrame);
        });
        editUser.setPreferredSize(new Dimension(150,25));
        editPanel.add(editUser);
        pane.add(editPanel);

        // Delete user panel
        JPanel deletePanel = new JPanel();
        JButton deleteUser = new JButton("Delete user");
        deleteUser.addActionListener(e ->{
            deleteUser(mainFrame);
        });
        deleteUser.setPreferredSize(new Dimension(150,25));
        deletePanel.add(deleteUser);
        pane.add(deletePanel);
    }

    /**
     * Creates a frame which open the search window for the user that needs to be edited
     * @param mainFrame frame from the initial screen
     * @return frame of new window
     */
    private JFrame editUserFrame(JFrame mainFrame){

        // Variables
        JPanel panel;
        JLabel label;

        // Frame
        frame = new JFrame("Edit User");
        frame.setSize(300,100);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        EnabledOnClose(frame, mainFrame);

        Container pane = frame.getContentPane();
        BoxLayout box = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(box);

        // Panel for editing user by searching username
        panel = new JPanel();
        label = new JLabel("Username: ");
        JTextField searchTField = new JTextField("", 10);
        JButton search = new JButton("Search");
        search.addActionListener(e -> {
            String searchThis = searchTField.getText();
            // Check for valid inputs
            if(searchThis.isEmpty()){
                JOptionPane.showMessageDialog(frame,"Enter a value to search!!!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!(a.getUser(searchThis) instanceof normalUser)){
                JOptionPane.showMessageDialog(frame,"Username is not in the list!!!",
                        "Warning",JOptionPane.WARNING_MESSAGE);
            } else {
                editUser(a.getUser(searchThis), frame);
            }
        });
        panel.add(label);
        panel.add(searchTField);
        panel.add(search);

        frame.add(panel);
        frame.setVisible(true);
        return frame;
    }

    /**
     * Creates a frame that has the details of the user search from the previous frame
     * @param uName user as object
     * @param prevFrame previous frame
     * @return return a frame
     */
    private JFrame editUser(normalUser uName, JFrame prevFrame){

        // Variables
        final Integer columnSize = 15;

        frame = new JFrame("Editing: "+uName.getUser());
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        EnabledOnClose(frame, prevFrame);

        Container pane = frame.getContentPane();
        BoxLayout box = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(box);

        // Organisation ID
        String[] orgNames = a.listOrg();
        JPanel orgPanel = new JPanel();
        label = new JLabel("Org Name: ");
        JComboBox orgNameList = new JComboBox(orgNames);
        orgNameList.setSelectedItem(a.getOrgByID(uName.getOrgID()).getName());
        orgPanel.add(label);
        orgPanel.add(orgNameList);

        // Username
        JPanel uPanel = new JPanel();
        label = new JLabel("Username: ");
        JTextField uTField = new JTextField(columnSize);
        uTField.setText(uName.getUser());
        uPanel.add(label);
        uPanel.add(uTField);

        // First name
        JPanel fPanel = new JPanel();
        label = new JLabel("First name: ");
        JTextField fTField = new JTextField(columnSize);
        fTField.setText(uName.getFN());
        fPanel.add(label);
        fPanel.add(fTField);

        // Last name
        JPanel lPanel = new JPanel();
        label = new JLabel("Last name: ");
        JTextField lTField = new JTextField(columnSize);
        lTField.setText(uName.getLN());
        lPanel.add(label);
        lPanel.add(lTField);

        // Password
        JPanel pwdPanel = new JPanel();
        label = new JLabel("Password: ");
        JPasswordField pwd = new JPasswordField("", columnSize);
        pwd.setText(uName.getHash());
        pwdPanel.add(label);
        pwdPanel.add(pwd);

        // Is admin panel
        JPanel isAdminPanel = new JPanel();
        JCheckBox isAdminCheckBox = new JCheckBox("Admin");
        if(uName.isAdmin()){
            isAdminCheckBox.setSelected(true);
        } else {
            isAdminCheckBox.setSelected(false);
        }
        isAdminCheckBox.addActionListener(e ->{
            if(isAdminCheckBox.isSelected()){
                orgPanel.setVisible(false);
            } else {
                orgPanel.setVisible(true);
            }
        });
        isAdminCheckBox.setBounds(100,100,50,50);
        isAdminPanel.add(isAdminCheckBox);

        // Panel for buttons
        JPanel bPanels = new JPanel();
        JButton edit = new JButton("Edit");
        edit.addActionListener(e -> {
            String orgName = (String) orgNameList.getSelectedItem();
            Integer orgID = a.getOrg(orgName).getID();
            String uN = uTField.getText();
            String fName = fTField.getText();
            String lName = lTField.getText();
            String p = new String(pwd.getPassword());
            // Check for valid inputs
            if (uN.isEmpty() || fName.isEmpty() || lName.isEmpty() || p.isEmpty()){
                JOptionPane.showMessageDialog(frame, "Enter a value for each section!!!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (a.getUser(uN) instanceof normalUser) {
                JOptionPane.showMessageDialog(frame, "Username already exist!!!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                if (isAdminCheckBox.isSelected()){
                    normalUser user1 = a.getUser(uName.getUser());
                    user1.setOrg_id(1);
                    user1.setUser(uN);
                    user1.setFN(fName);
                    user1.setLN(lName);
                    user1.setHash(p);
                    user1.setAdmin(true);
                    a.modifyUser(user1);
                } else {
                    normalUser user1 = a.getUser(uName.getUser());
                    user1.setOrg_id(orgID);
                    user1.setUser(uN);
                    user1.setFN(fName);
                    user1.setLN(lName);
                    user1.setHash(p);
                    user1.setAdmin(false);
                    a.modifyUser(user1);
                }
                JOptionPane.showMessageDialog(frame, "Successfully edited user!!!");
                frame.dispose();
                prevFrame.toFront();
                prevFrame.setEnabled(true);
            }
        });
        bPanels.add(edit);

        // Add panels to the frame
        frame.add(isAdminPanel);
        frame.add(orgPanel);
        frame.add(uPanel);
        frame.add(fPanel);
        frame.add(lPanel);
        frame.add(pwdPanel);
        frame.add(bPanels);

        frame.setVisible(true);
        return frame;
    }

    /**
     * Creates a frame that prompt for user name that needs to be deleted
     * @param mainFrame frame from the main screen
     * @return returns a JFrame
     */
    private JFrame deleteUser(JFrame mainFrame){

        // Variables
        final Integer columnSize = 15;

        frame = new JFrame("Delete user");
        frame.setSize(300,400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        EnabledOnClose(frame, mainFrame);

        Container pane = frame.getContentPane();
        BoxLayout box = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(box);

        // Panel for delete user
        JPanel dPanel = new JPanel();
        JLabel uName = new JLabel("Username: ");
        JTextField uField = new JTextField(columnSize);
        dPanel.add(uName);
        dPanel.add(uField);

        // Panel for buttons
        JPanel bPanel = new JPanel();
        JButton delete = new JButton("Delete");
        delete.addActionListener(e -> {
            if (uField.getText().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Enter  Username to delete !!!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!(a.getUser(uField.getText()) instanceof normalUser)){
                JOptionPane.showMessageDialog(frame, "Username does not exist !!!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                a.removeUser(uField.getText());
                JOptionPane.showMessageDialog(frame, "Successfully deleted user !!!");
            }
        });
        bPanel.add(delete);

        frame.add(dPanel);
        frame.add(bPanel);
        frame.setVisible(true);
        return frame;
    }

    /**
     * Creates a pain that contains the buttons for functionalities available for organisation that
     * admin user can perform
     * @param pane pain of the main screen
     * @param mainFrame frame of the main screen
     */
    private void adminOrgPane(Container pane, JFrame mainFrame){

        BoxLayout box = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(box);

        // Create admin user panel
        JPanel addOrgPanel = new JPanel();
        JButton createOrg = new JButton("Create org");
        createOrg.addActionListener(e ->{
            addOrg(mainFrame);
        });
        createOrg.setPreferredSize(new Dimension(150,25));
        addOrgPanel.add(createOrg);
        pane.add(addOrgPanel);

        // Edit user panel
        JPanel editPanel = new JPanel();
        JButton editOrg = new JButton("Edit org");
        editOrg.addActionListener(e ->{
            editOrgFrame(mainFrame);
        });
        editOrg.setPreferredSize(new Dimension(150,25));
        editPanel.add(editOrg);
        pane.add(editPanel);

        // Delete user panel
        JPanel deletePanel = new JPanel();
        JButton deleteOrg = new JButton("Delete org");
        deleteOrg.addActionListener(e ->{
            deleteOrg(mainFrame);
        });
        deleteOrg.setPreferredSize(new Dimension(150,25));
        deletePanel.add(deleteOrg);
        pane.add(deletePanel);
    }

    /**
     * Creates a frame for adding org
     * @param mainFrame frame from the main screen
     * @return returns a frame that prompts for org details that will be added to the database
     */
    private JFrame addOrg(JFrame mainFrame){

        // Variables
        JLabel label;
        Integer columnSize = 15;

        frame = new JFrame("Add Org");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        EnabledOnClose(frame, mainFrame);

        Container pane = frame.getContentPane();
        BoxLayout box = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(box);

        // Panel for organisation name
        JPanel orgNamePanel = new JPanel();
        label = new JLabel("Org name: ");
        JTextField orgName = new JTextField("", columnSize);
        orgNamePanel.add(label);
        orgNamePanel.add(orgName);

        // Panel for credit
        JPanel orgCreditPanel = new JPanel();
        label = new JLabel("Org Credit: ");
        JTextField orgCredit = new JTextField("", columnSize);
        orgCredit.setToolTipText("Enter only numeric digits(0-9)");
        orgCredit.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') ||
                        e.getKeyChar() == KeyEvent.VK_BACK_SPACE ||
                        e.getKeyChar() == KeyEvent.VK_DELETE){
                    orgCredit.setEditable(true);
                } else {
                    orgCredit.setEditable(false);
                }
            }
        });
        orgCreditPanel.add(label);
        orgCreditPanel.add(orgCredit);


        // Button panel
        JPanel panel = new JPanel();

        // Add button
        JButton add = new JButton("Add");
        add.addActionListener(e -> {
            if (orgName.getText().isEmpty() || orgCredit.getText().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Enter a value for each section !!!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                Integer credit = Integer.parseInt(orgCredit.getText());
                orgUnit org = new orgUnit(orgName.getText(), Integer.parseInt(orgCredit.getText()));
                // Add add org in the if statement
                if (a.addOrg(org)){
                    JOptionPane.showMessageDialog(frame, "Successfully added Organisation !!!");
                    orgName.setText("");
                    orgCredit.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Organisation name already exist !!!",
                            "Warning",JOptionPane.WARNING_MESSAGE);
                    orgName.setText("");
                }
            }
        });

        // Clear button
        JButton clear = new JButton("Clear");
        clear.addActionListener(e ->{
            orgName.setText("");
            orgCredit.setText("");
        });

        panel.add(add);
        panel.add(clear);

        // Add all panel to the container
        pane.add(orgNamePanel);
        pane.add(orgCreditPanel);
        pane.add(panel);

        frame.add(panel);
        frame.setVisible(true);

        return frame;
    }

    /**
     * Creates frame for editing org
     * @param mainFrame frame from the main frame
     * @return a frame that contains the detail of the org search and allows the admin
     * to change details
     */
    private JFrame editOrgFrame(JFrame mainFrame){

        // Variables
        JPanel panel;
        JLabel label;
        final Integer columnSize = 15;

        // Frame
        frame = new JFrame("Edit Org Credit");
        frame.setSize(300,225);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        EnabledOnClose(frame, mainFrame);

        Container pane = frame.getContentPane();
        BoxLayout box = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(box);

        // Org Credit
        JPanel creditPanel = new JPanel();
        label = new JLabel("Current Org Credit: ");
        JTextField cTField = new JTextField();
        cTField.setEditable(false);
        creditPanel.add(label);
        creditPanel.add(cTField);
        creditPanel.setVisible(false);

        //
        JTextField searchTField = new JTextField("", 10);

        // Org credit buttons
        JPanel addRemoveCredit = new JPanel();
        label = new JLabel("Amount:");
        JTextField amount = new JTextField(columnSize);
        JButton addCredit = new JButton("Add credit");
        addCredit.addActionListener(e -> {
            orgUnit unit = a.getOrg(searchTField.getText());
            Integer num = Integer.parseInt(amount.getText());
            u.editCredit(unit, num, true);
            JOptionPane.showMessageDialog(frame,"Adding credit successful!!!");
            cTField.setText(a.getOrg(searchTField.getText()).getCredits().toString());
        });
        JButton removeCredit = new JButton("Remove credit");
        removeCredit.addActionListener(e -> {
            orgUnit unit = a.getOrg(searchTField.getText());
            Integer num = Integer.parseInt(amount.getText());
            if (u.editCredit(unit, num, false)){
                JOptionPane.showMessageDialog(frame,"Removing credit successful!!!");
                cTField.setText(a.getOrg(searchTField.getText()).getCredits().toString());
            } else {
                JOptionPane.showMessageDialog(frame,"Enter a number lower than current credit!!!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        amount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') ||
                        e.getKeyChar() == KeyEvent.VK_BACK_SPACE ||
                        e.getKeyChar() == KeyEvent.VK_DELETE){
                    amount.setEditable(true);
                } else {
                    amount.setEditable(false);
                }
            }
        });
        addRemoveCredit.add(label);
        addRemoveCredit.add(amount);
        addRemoveCredit.add(addCredit);
        addRemoveCredit.add(removeCredit);
        addRemoveCredit.setVisible(false);

        // Panel for editing user by searching org name
        panel = new JPanel();
        label = new JLabel("Org name: ");
        JButton search = new JButton("Search");
        search.addActionListener(e -> {
            creditPanel.setVisible(false);
            addRemoveCredit.setVisible(false);
            amount.setText("");
            cTField.setText("");
            frame.setTitle("Edit Org Credit");
            String searchThis = searchTField.getText();
            //Boolean check = Arrays.stream(a.listOrg()).anyMatch(searchThis::equals);
            if(searchThis.isEmpty()){
                JOptionPane.showMessageDialog(frame,"Enter a org name to search!!!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!(Arrays.stream(a.listOrg()).anyMatch(searchThis::equals))){
                JOptionPane.showMessageDialog(frame,"Organisation is not in the list!!!",
                        "Warning",JOptionPane.WARNING_MESSAGE);
            } else {
                frame.setTitle("Editing org: " + searchThis);
                cTField.setText(a.getOrg(searchThis).getCredits().toString());
                creditPanel.setVisible(true);
                addRemoveCredit.setVisible(true);
            }
        });
        panel.add(label);
        panel.add(searchTField);
        panel.add(search);

        frame.add(panel);
        frame.add(creditPanel);
        frame.add(addRemoveCredit);
        frame.setVisible(true);
        return frame;
    }

    /**
     * Creates a delete frame
     * @param mainFrame frame from the main screen
     * @return frame that prompts admin for org and deletes the org from the database
     */
    private JFrame deleteOrg(JFrame mainFrame){

        // Variables
        final Integer columnSize = 15;

        frame = new JFrame("Delete Organisation");
        frame.setSize(300,125);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        EnabledOnClose(frame, mainFrame);

        Container pane = frame.getContentPane();
        BoxLayout box = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(box);

        // Panel for delete user
        JPanel dPanel = new JPanel();
        JLabel uName = new JLabel("Org name: ");
        JTextField uField = new JTextField(columnSize);
        dPanel.add(uName);
        dPanel.add(uField);

        // Panel for buttons
        JPanel bPanel = new JPanel();
        JButton delete = new JButton("Delete");
        delete.addActionListener(e -> {
            if (uField.getText().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Enter  Organisation to delete !!!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!(Arrays.stream(a.listOrg()).anyMatch(uField.getText()::equals))){
                JOptionPane.showMessageDialog(frame, "Organisation does not exist !!!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                //a.removeOrg(uField.getText());
                JOptionPane.showMessageDialog(frame, "Successfully deleted organisation !!!");
            }
        });
        bPanel.add(delete);

        frame.add(dPanel);
        frame.add(bPanel);
        frame.setVisible(true);
        return frame;
    }

    /**
     * Creates a pane that contains button for asset functionalities
     * @param pane pane of the main frame
     * @param mainFrame frame of the main screen
     */
    private void adminAssetPane(Container pane, JFrame mainFrame){

        BoxLayout box = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(box);

        // Create asset panel
        JPanel addOrgPanel = new JPanel();
        JButton createOrg = new JButton("Create asset");
        createOrg.addActionListener(e ->{
            addAsset(mainFrame);
        });
        createOrg.setPreferredSize(new Dimension(150,25));
        addOrgPanel.add(createOrg);
        pane.add(addOrgPanel);

        // Delete asset panel
        JPanel deletePanel = new JPanel();
        JButton deleteOrg = new JButton("Delete Asset");
        deleteOrg.addActionListener(e ->{
            deleteAsset(mainFrame);
        });
        deleteOrg.setPreferredSize(new Dimension(150,25));
        deletePanel.add(deleteOrg);
        pane.add(deletePanel);
    }

    /**
     * Creates a frame for adding asset
     * @param mainFrame frame from the main screen
     * @return returns a frame that prompts the admin user for asset details and add it to the database
     */
    private JFrame addAsset(JFrame mainFrame){
        // Variables
        JLabel label;
        JPanel orgPanel;
        Integer columnSize = 15;

        frame = new JFrame("Add Asset");
        frame.setSize(300, 225);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        EnabledOnClose(frame, mainFrame);

        Container pane = frame.getContentPane();
        BoxLayout box = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(box);

        // Panel for the asset ID
        String[] orgNames = a.listOrg();
        orgPanel = new JPanel();
        label = new JLabel("Org ID: ");
        JComboBox orgNameList = new JComboBox(orgNames);
        orgPanel.add(label);
        orgPanel.add(orgNameList);

        // Panel for user name
        JPanel assetNamePanel = new JPanel();
        label = new JLabel("Asset type: ");
        JTextField assetName = new JTextField("", columnSize);
        assetNamePanel.add(label);
        assetNamePanel.add(assetName);

        // Panel for first name
        JPanel assetQuantityPanel = new JPanel();
        label = new JLabel("Quantity: ");
        JTextField assetQuantityField = new JTextField("", columnSize);
        assetQuantityPanel.add(label);
        assetQuantityPanel.add(assetQuantityField);
        assetQuantityField.setToolTipText("Enter only numeric digits(0-9)");
        assetQuantityField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') ||
                        e.getKeyChar() == KeyEvent.VK_BACK_SPACE ||
                        e.getKeyChar() == KeyEvent.VK_DELETE){
                    assetQuantityField.setEditable(true);
                } else {
                    assetQuantityField.setEditable(false);
                }
            }
        });
        assetQuantityPanel.add(label);
        assetQuantityPanel.add(assetQuantityField);

        // Button panel
        JPanel panel = new JPanel();

        // Add button
        JButton add = new JButton("Add");
        add.addActionListener(e -> {
            String orgName = (String) orgNameList.getSelectedItem();
            Integer orgID = a.getOrg(orgName).getID();
            String asset = assetName.getText();
            String quantity = assetQuantityField.getText();
            if (asset.isEmpty() || quantity.isEmpty()){
                JOptionPane.showMessageDialog(frame, "Enter a value for each section!!!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (a.list_type().contains(asset)){
                JOptionPane.showMessageDialog(frame, "Asset type already exist!!!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                InventoryAsset newAsset = new InventoryAsset(orgID, asset, Integer.parseInt(quantity));
                a.addInvAsset(newAsset);
                JOptionPane.showMessageDialog(frame, "Asset successfully added to inventory!!!");
                assetName.setText("");
                assetQuantityField.setText("");
            }
        });

        // Clear button
        JButton clear = new JButton("Clear");
        clear.addActionListener(e ->{
            assetName.setText("");
            assetQuantityField.setText("");
        });

        panel.add(add);
        panel.add(clear);

        // Add all panel to the container
        pane.add(orgPanel);
        pane.add(assetNamePanel);
        pane.add(assetQuantityPanel);
        pane.add(panel);

        frame.add(panel);
        frame.setVisible(true);

        return frame;
    }

    /**
     * Creates a frame for deleting asset
     * @param mainFrame frame from the main screen
     * @return returns a frame that prompts for asset
     */
    private JFrame deleteAsset(JFrame mainFrame){

        // Variables
        final Integer columnSize = 15;

        frame = new JFrame("Delete asset");
        frame.setSize(300,125);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        EnabledOnClose(frame, mainFrame);

        Container pane = frame.getContentPane();
        BoxLayout box = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(box);

        // Panel for delete user
        JPanel dPanel = new JPanel();
        JLabel assetID = new JLabel("Asset ID: ");
        JTextField assetField = new JTextField("", columnSize);
        dPanel.add(assetID);
        dPanel.add(assetField);
        assetField.setToolTipText("Enter only numeric digits(0-9)");
        assetField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') ||
                        e.getKeyChar() == KeyEvent.VK_BACK_SPACE ||
                        e.getKeyChar() == KeyEvent.VK_DELETE){
                    assetField.setEditable(true);
                } else {
                    assetField.setEditable(false);
                }
            }
        });
        dPanel.add(assetID);
        dPanel.add(assetField);

        // Panel for buttons
        JPanel bPanel = new JPanel();
        JButton delete = new JButton("Delete");
        delete.addActionListener(e -> {
            if (assetField.getText().isEmpty()){
                JOptionPane.showMessageDialog(frame, "Enter asset to delete !!!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!(a.getInvAsset(Integer.parseInt(assetField.getText())) instanceof InventoryAsset)){
                JOptionPane.showMessageDialog(frame, "Asset does not exist !!!",
                        "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                a.removeInvAsset(Integer.parseInt(assetField.getText()));
                JOptionPane.showMessageDialog(frame, "Successfully delete asset !!!");
            }
        });
        bPanel.add(delete);

        frame.add(dPanel);
        frame.add(bPanel);
        frame.setVisible(true);
        return frame;
    }

    /**
     * Method for disabling previous frame and enabling it
     * @param currentFrame frame that is current open
     * @param previousFrame frame of the previous screen
     */
    private void EnabledOnClose(JFrame currentFrame,JFrame previousFrame){
        previousFrame.setEnabled(false);

        currentFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                currentFrame.dispose();
                previousFrame.dispose();
                adminUserFrame();
            };
        });
    }

}


