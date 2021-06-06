package client;


import common.assetUnit;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class userGuiClass {
    private JMenuBar mb;
    private JMenu home;
    private UserControls uc;
    private AdminControls ac;
    private normalUser user;


    public userGuiClass(normalUser user){
        this.user = user;
        uc = new UserControls();
        ac = new AdminControls();
        normalUserFrame();
    }

    private JFrame normalUserFrame(){
        JFrame frame = new JFrame("Electronic Asset Trading Platform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        //frame.setResizable(false);
        home = new JMenu("User Home Menu");
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
    }
    private void CreateListing(JFrame previousFrame){

        JFrame currentFrame = new JFrame("View all Listings");
        currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currentFrame.setSize(400, 200);
        currentFrame.setLocationRelativeTo(previousFrame);
        EnabledOnClose(currentFrame, previousFrame);

        JLabel listedAssetLabel = new JLabel("Select an Asset to Sell");

        String[] myAssets = uc.a.listInvAsset();
        JComboBox assetsAvailable = new JComboBox(myAssets);
    }
    private void ViewAllListings(JFrame previousFrame){
        JFrame currentFrame = new JFrame("View My Listings");
        currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currentFrame.setSize(400, 200);
        currentFrame.setLocationRelativeTo(previousFrame);
        EnabledOnClose(currentFrame, previousFrame);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout());

        assetUnit[] myListedAssetUnits = new assetUnit[]{new assetUnit()};
        // TODO update to take in all a user/organisations listings]);

        String[] columnNames = new String[]{"Asset Listed","Listed Price", "Actions"};

        JPanel table = createTable(columnNames, myListedAssetUnits, currentFrame);

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

        assetUnit[] myListedAssetUnits = new assetUnit[]{new assetUnit()};
        // TODO update to take in all a user/organisations listings
        String[] columnNames = new String[]{"Asset Listed","Listed Price"};

        JPanel table = createTable(columnNames, myListedAssetUnits, currentFrame);

        tablePanel.add(table);
        currentFrame.add(tablePanel);
        currentFrame.setVisible(true);


    }

    private void ViewListing(assetUnit listedAsset) {

    }

    private void ViewAllAssets(JFrame previousFrame) {

        JFrame currentFrame = new JFrame("Electronic Asset Trading Platform");
        currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currentFrame.setSize(400, 200);
        currentFrame.setLocationRelativeTo(previousFrame);

        EnabledOnClose(currentFrame, previousFrame);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new FlowLayout());

        assetUnit[] assetUnits = new assetUnit[]{new assetUnit()};
        // TODO update to take in all a user/organisations listings
        String[] columnNames = new String[]{"Asset Name","Asset Price"};

        JPanel table = createTable(columnNames, assetUnits, currentFrame);

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

    private JPanel createTable(Object[] columnNames, assetUnit[] dataArray , JFrame previousFrame){

        JPanel returnPanel = new JPanel( new FlowLayout(FlowLayout.CENTER, 0, 0) );
        JPanel tablePanel = new JPanel();
        JPanel ButtonPanel = new JPanel();
        Object[][] rowData = new Object[dataArray.length][];

        for (int i = 0; i < dataArray.length; i++) {
            int itemID = dataArray[i].getID();

            rowData[i] = new Object[]{};

            JButton viewAsset = new JButton("View");
            int finalI = i;
            viewAsset.addActionListener(e -> {
                ViewAsset(previousFrame, dataArray[finalI]);
            });
            ButtonPanel.add(viewAsset);

        }

        JTable table = new JTable(rowData, columnNames);
        table.removeEditor();

        TableCellRenderer buttonRenderer = new ButtonRenderer();
        table.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JTextField()));
        table.setRowSelectionAllowed(false);

        tablePanel.add(table);
        returnPanel.add(tablePanel);
        returnPanel.add(ButtonPanel);


        return returnPanel;
    }
    private void ViewAsset(JFrame previousFrame, assetUnit unit){
        JFrame currentFrame = new JFrame("Electronic Asset Trading Platform");
        currentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        currentFrame.setSize(400, 200);
        currentFrame.setLocationRelativeTo(previousFrame);

        EnabledOnClose(currentFrame, previousFrame);

        JButton buyButton = new JButton("Buy This");
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uc.buyItem(unit, user);
            }
        });
        JButton sellButton = new JButton("Sell This");
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uc.listItem(unit);
            }
        });
        currentFrame.setVisible(true);
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