package client;


import common.assetUnit;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class userGuiClass {
    private final UserControls uc;
    private final AdminControls ac;
    private final normalUser user;


    public userGuiClass(normalUser user){
        this.user = user;
        uc = new UserControls();
        ac = uc.a;
        normalUserFrame();
    }

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
    private void EnabledOnClose(JFrame currentFrame,JFrame previousFrame){
        previousFrame.setEnabled(false);

        currentFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                previousFrame.setEnabled(true);
            };
        });
    }
    private JPanel createAssetTable(Object[] columnNames, assetUnit[] dataArray , JFrame previousFrame){

        JPanel returnPanel = new JPanel( new FlowLayout(FlowLayout.CENTER, 0, 0) );
        JPanel tablePanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new BoxLayout(ButtonPanel, BoxLayout.Y_AXIS));
        Object[][] rowData = new Object[dataArray.length][];

        int height = 30;
        int width = 100;

        if ((dataArray.length > 0))
        {
            for (int i = 0; i < dataArray.length; i++) {

                int itemID = dataArray[i].getID();
                String itemName = uc.a.getInvAsset(dataArray[i].getAsset()).getType();
                String itemPrice = dataArray[i].getCredits().toString();
                String itemSeller = uc.a.getOrgByID(dataArray[i].getOrg()).getName();

                rowData[i] = new String[]{itemName, itemPrice, itemSeller};

                JButton viewAsset = new JButton("View");
                int finalI = i;
                viewAsset.addActionListener(e -> {
                    ViewAsset(previousFrame, dataArray[finalI]);
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
        String assetName = ac.getInvAsset(unit.getID()).getType();
        int userOrgID = user.getOrgID();
        int quantityAvailable = ac.getInvAsset(unit.getID()).getQTY();
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

    public assetUnit[] mergeAssetArrays(assetUnit[] firstArray, assetUnit[] secondArray){

        int fal = firstArray.length;        //determines length of firstArray
        int sal = secondArray.length;   //determines length of secondArray
        assetUnit[] result = new assetUnit[fal + sal];  //resultant array of size first array and second array
        System.arraycopy(firstArray, 0, result, 0, fal);
        System.arraycopy(secondArray, 0, result, fal, sal);
        return result;
    }
}
//BUTTON RENDERER CLASS
class ButtonRenderer extends JButton implements  TableCellRenderer
{

    //CONSTRUCTOR
    public ButtonRenderer() {
        //SET BUTTON PROPERTIES
        setOpaque(true);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object obj,
                                                   boolean selected, boolean focused, int row, int col) {

        //SET PASSED OBJECT AS BUTTON TEXT
        setText((obj==null) ? "":obj.toString());

        return this;
    }

}
//BUTTON EDITOR CLASS
class ButtonEditor extends DefaultCellEditor
{
    protected JButton btn;
    private String lbl;
    private Boolean clicked;

    public ButtonEditor(JTextField txt) {
        super(txt);

        btn=new JButton();
        btn.setOpaque(true);

        //WHEN BUTTON IS CLICKED
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                fireEditingStopped();
            }
        });
    }

    //OVERRIDE A COUPLE OF METHODS
    @Override
    public Component getTableCellEditorComponent(JTable table, Object obj, boolean selected, int row, int col) {

        //SET TEXT TO BUTTON,SET CLICKED TO TRUE,THEN RETURN THE BTN OBJECT
        lbl=(obj==null) ? "":obj.toString();
        btn.setText(lbl);
        clicked=true;
        return btn;
    }

    //IF BUTTON CELL VALUE CHANGES,IF CLICKED THAT IS
    @Override
    public Object getCellEditorValue() {

        if(clicked)
        {
            //SHOW US SOME MESSAGE
            JOptionPane.showMessageDialog(btn, lbl+" Clicked");
        }
        //SET IT TO FALSE NOW THAT ITS CLICKED
        clicked=false;
        return new String(lbl);
    }

    @Override
    public boolean stopCellEditing() {

        //SET CLICKED TO FALSE FIRST
        clicked=false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {

        super.fireEditingStopped();
    }
}

