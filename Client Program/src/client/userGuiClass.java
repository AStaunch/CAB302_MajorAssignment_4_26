package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class userGuiClass {
    private JMenuBar mb;
    private JMenu home;


    public userGuiClass(/* User Object*/){
        normalUserFrame();
    }

    private JFrame normalUserFrame(){
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

        userHomePane(frame);
        return frame;
    }

    private void userHomePane(JFrame frame){

        Container pane = frame.getContentPane();
        BoxLayout box = new BoxLayout(pane, BoxLayout.Y_AXIS);
        pane.setLayout(box);

        // Create a listing Panel
        JPanel createListingPanel = new JPanel();
        JButton createListing = new JButton("Create New Listing");
        createListing.setPreferredSize(new Dimension(150,25));
        createListingPanel.add(createListing);
        pane.add(createListingPanel);

        createListing.addActionListener(e -> {
            CreateListing(frame);
        });

        // View All Public Listings
        JPanel viewListingsPanel = new JPanel();
        JButton viewListings = new JButton("View all Listings");
        viewListings.setPreferredSize(new Dimension(150,25));
        viewListingsPanel.add(viewListings);
        pane.add(viewListingsPanel);

        viewListings.addActionListener(e -> {
            ViewAllListings(frame);
        });

        // View + edit my Current Listings
        JPanel mylistingsPanel = new JPanel();
        JButton myListings = new JButton("Edit user");
        myListings.setPreferredSize(new Dimension(150,25));
        mylistingsPanel.add(myListings);
        pane.add(mylistingsPanel);

        myListings.addActionListener(e -> {
            ViewMyListings(frame);
        });

        // List all Assets
        JPanel listAssetsPanel = new JPanel();
        JButton listAssets = new JButton("Delete user");
        listAssets.setPreferredSize(new Dimension(150,25));
        listAssetsPanel.add(listAssets);
        pane.add(listAssetsPanel);

        listAssets.addActionListener(e -> {
            ViewAllAssets(frame);
        });
    }
    private void CreateListing(JFrame previousFrame){

        JFrame frame = new JFrame("Create a New Listing");
        EnabledOnClose(frame, previousFrame);

    }
    private void ViewAllListings(JFrame previousFrame){
        JFrame frame = new JFrame("View all Listings");
        EnabledOnClose(frame, previousFrame);
    }
    private void ViewMyListings(JFrame previousFrame){
        JFrame frame = new JFrame("View My Listings");
        EnabledOnClose(frame, previousFrame);
    }
    private void ViewAllAssets(JFrame previousFrame) {
        JFrame frame = new JFrame("View All Assets");
        EnabledOnClose(frame, previousFrame);

        //userObjects[] userObjects =
        int noObjects = 5;
        Object[][] rowData = new Object[noObjects][];
        for (int i = 0; i < noObjects; i++){
            JButton button = new JButton();
            rowData[i] = new Object[]{"Change Me to Objects[i].getName", "Change Me to Objects[i].getName", "View Object Button"};
        }

        String[] columnNames = new String[]{"Asset Name","Asset Price", ""};
        JTable table = new JTable();

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
