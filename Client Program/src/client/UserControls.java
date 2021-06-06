package client;

import JDBC.DBConnection;
import common.InventoryAsset;
import common.assetUnit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserControls {
    AdminControls a = new AdminControls();

    private Connection connection = DBConnection.getInstance();
    public static String LIST_ITEM = "INSERT INTO list_item (org_id, seller_id, asset_id, quantity," +
            "credit) VALUES (?,?,?,?,?)";
    public static String REMOVE_ITEM = "REMOVE FROM list_item WHERE id=?";
    public static String GET_ITEM = "SELECT * FROM list_item WHERE id=?";
    public static String VIEW_ITEMS = "SELECT * FROM list_item";
    public static String VIEW_MYLISTING = "SELECT * FROM list_item WHERE seller_id=?";
    public static String VIEW_ORGLISTING = "SELECT * FROM list_item WHERE org_id=?";
    public static String BUY_ITEM_NEW = "INSERT INTO inventory (org_id, type, quantity) VALUES = (?,?,?)";
    public static String BUY_ITEM = "UPDATE inventory SET quantity = ? WHERE org_id = ? AND type = ?";
    public static String EDIT_CREDIT = "UPDATE organisations SET credits = ? WHERE id=?";
    public static String ADD_TRANSACTION = "INSERT INTO transaction (org_id, seller_id, asset_id, quantity," +
            "credit,buyer_id) VALUES (?,?,?,?,?,?) ";

    private PreparedStatement listItem;
    private PreparedStatement removeItem;
    private PreparedStatement getItem;
    private PreparedStatement viewItems;
    private PreparedStatement viewListing;
    private PreparedStatement viewOrgListing;
    private PreparedStatement buyNewItem;
    private PreparedStatement buyItem;
    private PreparedStatement editCredit;
    private PreparedStatement addTransaction;

    public UserControls() {
        try {
            Statement st = this.connection.createStatement();
            this.listItem = this.connection.prepareStatement(LIST_ITEM);
            this.removeItem = this.connection.prepareStatement(REMOVE_ITEM);
            this.getItem = this.connection.prepareStatement(GET_ITEM);
            this.viewItems = this.connection.prepareStatement(VIEW_ITEMS);
            this.viewListing = this.connection.prepareStatement(VIEW_MYLISTING);
            this.viewOrgListing = this.connection.prepareStatement(VIEW_ORGLISTING);
            this.buyNewItem = this.connection.prepareStatement(BUY_ITEM_NEW);
            this.buyItem = this.connection.prepareStatement(BUY_ITEM);
            this.editCredit = this.connection.prepareStatement(EDIT_CREDIT);
            this.addTransaction = this.connection.prepareStatement(ADD_TRANSACTION);

        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    public List<assetUnit> listAsset() {
        ResultSet rs = null;
        List<assetUnit> listAssetUnit = new ArrayList<assetUnit>();

        try {
            rs = this.viewItems.executeQuery();

            while(rs.next()) {
                Integer id = rs.getInt(1);
                Integer org_id = rs.getInt(2);
                Integer seller_id = rs.getInt(3);
                Integer asset_id = rs.getInt(4);
                Integer quantity = rs.getInt(5);
                Integer credits = rs.getInt(5);

                assetUnit aU = new assetUnit(id, org_id, seller_id, asset_id, quantity, credits);
                listAssetUnit.add(aU);
            }
        }
        catch (SQLException e) {

        }
        return listAssetUnit;
    }

    public List<assetUnit> listMyListings(normalUser u) {
        ResultSet rs = null;
        List<assetUnit> listAssetUnit = new ArrayList<assetUnit>();

        try {
            this.viewListing.setInt(1, u.getID());
            rs = this.viewListing.executeQuery();

            while(rs.next()) {
                Integer id = rs.getInt(1);
                Integer org_id = rs.getInt(2);
                Integer seller_id = rs.getInt(3);
                Integer asset_id = rs.getInt(4);
                Integer quantity = rs.getInt(5);
                Integer credits = rs.getInt(5);

                assetUnit aU = new assetUnit(id, org_id, seller_id, asset_id, quantity, credits);
                listAssetUnit.add(aU);
            }
        }
        catch (SQLException e) {

        }
        return listAssetUnit;
    }

    public List<assetUnit> listOrgListings(orgUnit o) {
        ResultSet rs = null;
        List<assetUnit> listAssetUnit = new ArrayList<assetUnit>();

        try {
            this.viewOrgListing.setInt(1, o.getID());
            rs = this.viewOrgListing.executeQuery();

            while(rs.next()) {
                Integer id = rs.getInt(1);
                Integer org_id = rs.getInt(2);
                Integer seller_id = rs.getInt(3);
                Integer asset_id = rs.getInt(4);
                Integer quantity = rs.getInt(5);
                Integer credits = rs.getInt(5);

                assetUnit aU = new assetUnit(id, org_id, seller_id, asset_id, quantity, credits);
                listAssetUnit.add(aU);
            }
        }
        catch (SQLException e) {

        }
        return listAssetUnit;
    }

    public Boolean generalBuy(assetUnit u, normalUser user) {
        // Check for sufficient funds
        if (a.getOrgByID(user.getOrgID()).getCredits()>=u.getQTY())
        {
            InventoryAsset inv = a.getInvAssetTO(a.getInvAsset(u.getAsset()).getType(), user.getOrgID());
            if (inv == null) {
                buyNewItem(u, user);
            }
            else {
                buyItem(u, user);
            }
            return Boolean.TRUE;
        }
        else {
            return Boolean.FALSE;
        }

    }

    public void buyItem(assetUnit u, normalUser user) {
        try {
            InventoryAsset inv = a.getInvAssetTO(a.getInvAsset(u.getAsset()).getType(), user.getOrgID());
            this.buyItem.setInt(1, inv.getQTY()+u.getQTY());
            this.buyItem.setString(3, a.getInvAsset(u.getAsset()).getType());
            this.listItem.execute();

            // Seller changes credits for sellers org
            editCredit(a.getOrgByID(u.getOrg()), u.getCredits(), Boolean.TRUE );

            // Seller loses inventory

            // Buyer changes credits for buyers org
            editCredit(a.getOrgByID(user.getOrgID()), u.getCredits(), Boolean.FALSE);

            // Change inventory for buyer
            InventoryAsset ia = a.getInvAsset(u.getAsset());
            // Sets new quantity by current QTY - asset QTY
            ia.setQTY(ia.getQTY() - u.getQTY());
            a.editInv(ia);

            // creates receipt and stores in db
            AddTransaction(u, user);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // user buys u
    public void buyNewItem(assetUnit u, normalUser user) {

        try {
            this.buyNewItem.setInt(1,user.getOrgID());
            this.buyNewItem.setString(2,a.getInvAsset(u.getAsset()).getType());
            this.buyNewItem.setInt(3, u.getQTY());
            this.listItem.execute();

            // Seller changes credits for sellers org
            editCredit(a.getOrgByID(u.getOrg()), u.getCredits(), Boolean.TRUE );

            // Buyer changes credits for buyers org
            editCredit(a.getOrgByID(user.getOrgID()), u.getCredits(), Boolean.FALSE);

            // creates receipt and stores in db
            AddTransaction(u, user);

            // Change inventory for buyer
            InventoryAsset ia = a.getInvAsset(u.getAsset());
            // Sets new quantity by current QTY - asset QTY
            ia.setQTY(ia.getQTY() - u.getQTY());
            a.editInv(ia);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCredit(orgUnit o, Integer amount, Boolean tf) {
        try {
            if (tf == Boolean.TRUE) {
                this.editCredit.setInt(1,o.getCredits()+amount);
            }
            else {
                this.editCredit.setInt(1,o.getCredits()-amount);
            }
            this.editCredit.setInt(2, o.getID());
            this.editCredit.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void AddTransaction(assetUnit u, normalUser user){
        try {
            this.addTransaction.setInt(1, u.getOrg());
            this.addTransaction.setInt(2, u.getSeller());
            this.addTransaction.setInt(3, u.getAsset());
            this.addTransaction.setInt(4,u.getQTY());
            this.addTransaction.setInt(5,u.getCredits());
            this.addTransaction.setInt(6, user.getID());
            this.addTransaction.execute();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listItem(assetUnit u) {
        try {
            this.listItem.setInt(1, u.getOrg());
            this.listItem.setInt(2, u.getSeller());
            this.listItem.setInt(3, u.getAsset());
            this.listItem.setInt(4, u.getQTY());
            this.listItem.setInt(5, u.getCredits());
            this.listItem.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeItem(Integer id) {
        try {
            this.removeItem.setInt(1, id);
            this.removeItem.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public assetUnit getItem(Integer id) {
        assetUnit a = new assetUnit();
        ResultSet rs = null;
        try {
            this.getItem.setInt(1, id);
            rs = this.getItem.executeQuery();
            rs.next();
            a.setID(rs.getInt(1));
            a.setOrg(rs.getInt(2));
            a.setSeller(rs.getInt(3));
            a.setAsset(rs.getInt(4));
            a.setQTY(rs.getInt(5));
            a.setCredits(rs.getInt(6));
            return a;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return a;
    }

}
