import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class guiClass {
    private JFrame frame;

    private JButton loginButton;
    private JTextField userName;
    private  JTextField password;

    public guiClass( ) { //Should Probs have a data source injected here?



        frame = new JFrame("Electronic Asset Trading Platform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300,300));
        frame.setSize(new Dimension(500,300));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        this.initGUI();
    }

    private void initGUI() {
        JPanel loginTextPanel = new JPanel();
        loginTextPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        loginTextPanel.setLayout(new BoxLayout(loginTextPanel, BoxLayout.Y_AXIS));

        frame.add(loginTextPanel, BorderLayout.WEST);

        loginButton = new JButton("Log In");
        loginTextPanel.add(loginButton);

        JLabel userText = new JLabel("Username: ");
        loginTextPanel.add(userText);


        JLabel passText = new JLabel("Password: ");
        loginTextPanel.add(passText);


        JPanel loginEntryPanel = new JPanel();
        loginEntryPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        loginEntryPanel.setLayout(loginTextPanel.getLayout());
        frame.add(loginEntryPanel, BorderLayout.CENTER);

        JTextField username = new JTextField();
        username.setMinimumSize(new Dimension(200, 20));
        username.setMaximumSize(new Dimension(200, 20));
        loginEntryPanel.add(username);


        JTextField password = new JTextField();
        password.setMaximumSize(new Dimension(200, 20));
        password.setMinimumSize(new Dimension(200, 20));
        loginEntryPanel.add(password);

    }

//    private class ButtonListener implements ActionListener {
//
//        /**
//         * @see ActionListener#actionPerformed(ActionEvent)
//         */
//        public void actionPerformed(ActionEvent e) {
//            int size = data.getSize();
//
//            JButton source = (JButton) e.getSource();
//            if (source == newButton) {
//                newPressed();
//            } else if (source == saveButton) {
//                savePressed();
//            } else if (source == deleteButton) {
//                deletePressed();
//            }
//        }
//    }

//    private JPanel makeLoginPanel() {
//        JPanel detailsPanel = new JPanel();
//        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.X_AXIS));
//        detailsPanel.add(Box.createHorizontalStrut(20));
//        detailsPanel.add(makeNameListPane());
//        detailsPanel.add(Box.createHorizontalStrut(20));
//        detailsPanel.add(makeAddressFieldsPanel());
//        detailsPanel.add(Box.createHorizontalStrut(20));
//        return detailsPanel;
//    }







    /*
    privat
    Container contentPane = this.getContentPane();
      contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));


    private JPanel initPane() {
        JPanel detailsPane = new JPanel();
        detailsPane.setLayout(new BoxLayout(detailsPane, ));

        return;
    }
    */
}