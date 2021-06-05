package client;
import javax.swing.*;
import java.awt.*;

public class adminGuiClass {
    private JMenuBar mb;
    private JMenu home;

    public adminGuiClass(){
        adminUserFrame();
    }

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

        adminHomePane(frame.getContentPane());
        return frame;
    }

    private void addAdminPane(Container pane){
        // Variable
        String[] labels = {"User ID: ","Organisation ID: ","Username: ", "First name: ", "Last name: " +
                "Password: ","Birthday: "};
    }
    private void adminHomePane(Container pane){

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
