package client;


import common.assetUnit;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A Class for creating and managing the GUI for non-admin users
 * Has a private UserControl and User Object used to access data and functions
 */
public class userGuiClass {
    private final UserControls uc;
    private final normalUser user;

    /**
     * Create an Instance of the userGuiClass
     * @param user User Object of the non-admin user accessing the Program;
     *             passed in during login.
     *             Creates a set of control objects for the functions to herein to use.
     */
    public userGuiClass(normalUser user){
        this.user = user;
        uc = new UserControls();
        normalUserFrame();
    }

    /**
     * Initalises the landing Frame of the user GUI
     * @return  The JFrame created
     */
    private JFrame normalUserFrame(){
        JFrame frame = new JFrame("Electronic Asset Trading Platform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        //frame.setResizable(false);
        JMenu home = new JMenu("User Home Menu");
        JMenuBar mb = new JMenuBar();
        mb.add(home);

        frame.setJMenuBar(mb);
        frame.setVisible(true);

        userHomePane(frame);
        return frame;
    }

    /**
     * Fills a frame, creating the Landing Page for the user
     * @param frame JFrame passed on from the Frame Before
     *
     */
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

        createListing.addActionListener(e -> CreateListing(frame));

        // View All Public Listings
        JPanel viewListingsPanel = new JPanel();
        JButton viewListings = new JButton("View all Listings");
        viewListings.setPreferredSize(new Dimension(150,25));
        viewListingsPanel.add(viewListings);
        pane.add(viewListingsPanel);

        viewListings.addActionListener(e -> userGuiClass.this.ViewAllListings(frame));

        // View + edit my Current Listings
        JPanel mylistingsPanel = new JPanel();
        JButton myListings = new JButton("Edit my Listings");
        myListings.setPreferredSize(new Dimension(150,25));
        mylistingsPanel.add(myListings);
        pane.add(mylistingsPanel);

        myListings.addActionListener(e -> {
            ViewMyListings(frame);
        });

        // List all Assets
        JPanel listAssetsPanel = new JPanel();
        JButton listAssets = new JButton("View All Assets");
        listAssets.setPreferredSize(new Dimension(150,25));
        listAssetsPanel.add(listAssets);
        pane.add(listAssetsPanel);

        listAssets.addActionListener(e -> {
            ViewAllAssets(frame);
        });


        // Create a listing Panel
        JPanel editPasswordPanel = new JPanel();
        JButton editPassword = new JButton("Create New Listing");
        editPassword.setPreferredSize(new Dimension(150,25));
        editPasswordPanel.add(editPassword);
        pane.add(editPasswordPanel);

        editPassword.addActionListener(e -> {
            EditPassword(frame);
        });
    }

    /**
     * Opens the Frame to edit the User Object's password
     * @param previousFrame JFrame passed on from the Frame Before
     */
    private void EditPassword(JFrame previousFrame) {
        JFrame currentFrame = new JFrame("View all Listings");
        currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currentFrame.setSize(400, 200);
        currentFrame.setLocationRelativeTo(previousFrame);
        EnabledOnClose(currentFrame, previousFrame);

        JPanel textPanel = new JPanel(new BoxLayout(currentFrame.getContentPane(), BoxLayout.Y_AXIS ));
        JLabel current = new JLabel("Please enter you current Password:");
        JTextField currentPW = new JTextField("Current Password");
        JLabel newP = new JLabel("Please enter you current Password:");
        JTextField newPW = new JTextField("Current Password");
        JLabel repeat = new JLabel("Please enter you current Password:");
        JTextField repeatPW = new JTextField("Current Password");

    }

    /**
     * Opens the Frame to Create a new Buy or Sell listing
     * @param previousFrame JFrame passed on from the Frame Before
     */
    private void CreateListing(JFrame previousFrame){

        JFrame currentFrame = new JFrame("View all Listings");
        currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currentFrame.setSize(400, 200);
        currentFrame.setLocationRelativeTo(previousFrame);
        EnabledOnClose(currentFrame, previousFrame);
        currentFrame.setVisible(true);

        JPanel panel = new JPanel();
        JLabel listedAssetLabel = new JLabel("Select an Asset to Sell");

        String[] assetNames = uc.a.list_type().toArray(new String[0]);

        JComboBox assetsAvailable = new JComboBox(assetNames);
        JTextField qtyBox = new JTextField();
        JTextField listBox = new JTextField();


        JCheckBox isBuy = new JCheckBox("Is this a buy Listing?");
        isBuy.setEnabled(false);
        JButton done = new JButton("Create Listing!");
        done.addActionListener(e -> {

            int assetID = 1; //place holder

            int qty = java.lang.Integer.parseInt(qtyBox.toString());
            int listPrice = java.lang.Integer.parseInt(listBox.toString());
            if(isInteger(qtyBox.toString()) && isInteger(qtyBox.toString())){
                assetUnit asset = new assetUnit(user.getOrgID(),user.getID(), assetID, qty , listPrice, isBuy.isSelected());
            }else{

            }

        });

        panel.add(qtyBox);
        currentFrame.add(panel);
    }

    /**
     * Opens a frame to views all the listings in the Database, first listing the Buy then Sell listings
     * @param previousFrame JFrame passed on from the Frame Before
     */
    private void ViewAllListings(JFrame previousFrame){
        JFrame currentFrame = new JFrame("View My Listings");
        currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currentFrame.setSize(400, 200);
        currentFrame.setLocationRelativeTo(previousFrame);
        EnabledOnClose(currentFrame, previousFrame);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout());

        //Array of Buy Orders
        assetUnit[] allListedBuys = uc.viewListing(true).toArray(new assetUnit[0]);
        //Array pf Sell Orders
        assetUnit[] allListedSells = uc.viewListing(false).toArray(new assetUnit[0]);

        assetUnit[] allListedAssets = mergeAssetArrays(allListedBuys, allListedSells);

        // TODO update to take in all a user/organisations listings]);

        String[] columnNames = new String[]{"Asset Listed", "Listed Price", "Actions"};

        JPanel table = createAssetTable(columnNames, allListedAssets, currentFrame);
        tablePanel.add(table);
        currentFrame.add(tablePanel);
        currentFrame.setVisible(true);
    }

    /**
     * Opens a frame to view all the listings of the Users Organisation
     * @param previousFrame JFrame passed on from the Frame Before
     */
    private void ViewMyListings(JFrame previousFrame){
        JFrame currentFrame = new JFrame("View My Listings");
        currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currentFrame.setSize(400, 200);
        currentFrame.setLocationRelativeTo(previousFrame);
        EnabledOnClose(currentFrame, previousFrame);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout());

        assetUnit[] myListedAsset = uc.viewListing(true).toArray(new assetUnit[0]);
        // TODO update to take in all a user/organisations listings
        String[] columnNames = new String[]{"Asset Listed","Listed Price"};

        JPanel table = createAssetTable(columnNames, myListedAsset, currentFrame);

        tablePanel.add(table);
        currentFrame.add(tablePanel);
        currentFrame.setVisible(true);


    }

    /**
     * Opens a frame to view all the Assets in the database, giving the option to open an asset's View page
     * @param previousFrame JFrame passed on from the Frame Before
     */
    private void ViewAllAssets(JFrame previousFrame) {

        JFrame currentFrame = new JFrame("Electronic Asset Trading Platform");
        currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currentFrame.setSize(400, 200);
        currentFrame.setLocationRelativeTo(previousFrame);

        EnabledOnClose(currentFrame, previousFrame);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout());

        String[] assetInfo = uc.a.listInvAsset();
        String[][] rowData = new String[assetInfo.length][];
        for(int i = 0; i < assetInfo.length; i++){
            rowData[i] = assetInfo[i].split(", ", 3);
        }


        // TODO update to take in all a user/organisations listings
        String[] columnNames = new String[]{"Asset Name","Asset Price"};

        JTable table = new JTable(rowData, columnNames);
        //JPanel table = createAssetTable(columnNames, assetUnits, currentFrame);

        tablePanel.add(new JScrollPane(table));
        tablePanel.add(table);
        currentFrame.add(tablePanel);
        currentFrame.setVisible(true);
    }

    /**
     * Opens a frame containing information about the asset, and the option to buy or sell it
     * @param previousFrame JFrame passed on from the Frame Before
     * @param unit The assetUnit that is being viewed
     */
    private void ViewAsset(JFrame previousFrame, assetUnit unit){
        JFrame currentFrame = new JFrame("Electronic Asset Trading Platform");
        currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currentFrame.setSize(400, 200);
        currentFrame.setLocationRelativeTo(previousFrame);

        EnabledOnClose(currentFrame, previousFrame);

        JPanel mainPanel = new JPanel(new FlowLayout());
        Container pane = currentFrame.getContentPane();
        JPanel buyPanel = new JPanel();
        buyPanel.setLayout(new BoxLayout(buyPanel, BoxLayout.Y_AXIS));
        JPanel sellPanel = new JPanel();
        sellPanel.setLayout(new BoxLayout(sellPanel, BoxLayout.Y_AXIS));
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));



        //Asset Information
        String assetName = uc.a.getInvAsset(unit.getID()).getType();
        int userOrgID = user.getOrgID();
        int quantityAvailable = uc.a.getInvAsset(unit.getID()).getQTY();
        int quantityOwned;
        try {
            quantityOwned = uc.a.getInvAssetTO(assetName, userOrgID).getQTY();
        }catch (Exception e){
            quantityOwned = 0;
        }
        int lowestPrice = 1;
        //TODO

        JLabel unitName = new JLabel(assetName);
        JLabel unitQty = new JLabel("# Units up for sale: " + quantityAvailable );
        JLabel unitStartPrice = new JLabel("Starting Price: " + lowestPrice);

        infoPanel.add(unitName);
        infoPanel.add(unitQty);
        infoPanel.add(unitStartPrice);

        //Buy Functions
        //Type, Qty, Credits/unit
        JTextField qtyBuy = new JTextField("Please Enter a Quantity to buy");
        JTextField priceBuy = new JTextField("Please Enter a Price Per Unit to buy at");

        JButton buyButton = new JButton("Buy This");
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int qtyBought = Integer.parseInt(qtyBuy.toString());
                Boolean goodQty = quantityAvailable >= qtyBought && isInteger(qtyBuy.toString());

                Boolean goodPrice = true;
                //TODO
                if (goodQty && goodPrice){
                    uc.generalBuy(unit, user, qtyBought);
                }else if(!goodQty){
                    JDialog popup = new JDialog();
                    JOptionPane.showMessageDialog(null, "Please Enter a valid Quantity");
                }else if(!goodPrice){
                    JOptionPane.showMessageDialog(null, "Please Enter a valid Price.");
                }
            }
        });
        buyPanel.add(qtyBuy);
        buyPanel.add(priceBuy);
        buyPanel.add(buyButton);

        //Sell Functions
        //Type, Qty, stockSold
        JTextField qtySell = new JTextField("Please Enter a Quantity to sell");
        JTextField priceSell = new JTextField("Please Enter a Price Per Unit to sell at");

        JButton sellButton = new JButton("Sell This");
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int qtySold = Integer.parseInt(qtySell.toString());
                Boolean goodQty = isInteger(qtySell.toString());

                int sellPrice = Integer.parseInt(priceSell.toString());
                Boolean goodPrice = isInteger(priceSell.toString());
                unit.setCredits(sellPrice);
                //TODO
                if (goodQty && goodPrice){
                    for(int i = 0; i < qtySold; i++){
                        uc.listItem(unit);
                    }

                }else if(!goodQty){
                    JDialog popup = new JDialog();
                    JOptionPane.showMessageDialog(null, "Please Enter a valid Quantity");
                }else if(!goodPrice){
                    JOptionPane.showMessageDialog(null, "Please Enter a valid Price.");
                }
            }
        });
        sellPanel.add(qtySell);
        sellPanel.add(priceSell);
        sellPanel.add(sellButton);

        //Add To Main Panel
        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(buyPanel, BorderLayout.EAST);
        mainPanel.add(sellPanel, BorderLayout.WEST);
        currentFrame.add(mainPanel);
        currentFrame.setVisible(true);
    }

    /**
     * Disables the previous frame and sets at to enable when the current frame is closed
     * @param currentFrame JFrame that was just opened
     * @param previousFrame JFrame that is behind the new Frame
     */
    private void EnabledOnClose(JFrame currentFrame,JFrame previousFrame){
        previousFrame.setEnabled(false);

        currentFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                previousFrame.setEnabled(true);
            };
        });
    }

    /**
     * Creates a Table of assets with buttons that open them using ViewAsset()
     * @param columnNames the headers of the Table
     * @param assetArray the array of Assets that the table's data is constructed from
     * @param previousFrame passed into the ButtonListeners that create ViewAsset Frames
     * @return returns a panel contain all the buttons and the table
     */
    private JPanel createAssetTable(Object[] columnNames, assetUnit[] assetArray , JFrame previousFrame){

        JPanel returnPanel = new JPanel( new FlowLayout(FlowLayout.CENTER, 0, 0) );
        JPanel tablePanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new BoxLayout(ButtonPanel, BoxLayout.Y_AXIS));
        Object[][] rowData = new Object[assetArray.length][];

        int height = 30;
        int width = 100;

        if ((assetArray.length > 0))
        {
            for (int i = 0; i < assetArray.length; i++) {

                int itemID = assetArray[i].getID();
                String itemName = uc.a.getInvAsset(assetArray[i].getAsset()).getType();
                String itemPrice = assetArray[i].getCredits().toString();
                String itemSeller = uc.a.getOrgByID(assetArray[i].getOrg()).getName();

                rowData[i] = new String[]{itemName, itemPrice, itemSeller};

                JButton viewAsset = new JButton("View");
                int finalI = i;
                viewAsset.addActionListener(e -> {
                    ViewAsset(previousFrame, assetArray[finalI]);
                });
                viewAsset.setPreferredSize(new Dimension(width, height));
                ButtonPanel.add(viewAsset);

            }

            JTable table = new JTable(rowData, columnNames);
            table.setRowHeight(height);

            TableColumnModel columnModel = table.getColumnModel();
            for(int i = 0; i < columnNames.length; i++){
                columnModel.getColumn(i).setWidth(width);
            }

            table.removeEditor();
            table.setRowSelectionAllowed(false);
            tablePanel.add(new JScrollPane(table));

            tablePanel.add(table);
            returnPanel.add(tablePanel);
            returnPanel.add(ButtonPanel);


        }else{
            returnPanel.add(new JLabel("There are no Assets that fit this criteria"));
        }
        return returnPanel;
    }

    /**
     * Checks if a string can be parsed as the intended integer (i.e string "1" == int 1)
     * @param str the string that is being parsed
     * @return Returns True if the string is the inteded integer and False if it is not
     */
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * Merges two AssetArrays into a single array. It is here to clean up other methods
     * @param firstArray The first assetUnit array to be merged.
     * @param secondArray The second assetUnit array to be merged.
     * @return Returns the combined assetUnit arrays
     */
    public assetUnit[] mergeAssetArrays(assetUnit[] firstArray, assetUnit[] secondArray){

        int fal = firstArray.length;        //determines length of firstArray
        int sal = secondArray.length;   //determines length of secondArray
        assetUnit[] result = new assetUnit[fal + sal];  //resultant array of size first array and second array
        System.arraycopy(firstArray, 0, result, 0, fal);
        System.arraycopy(secondArray, 0, result, fal, sal);
        return result;
    }
}