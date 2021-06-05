package client;

import JDBC.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserControls {

    private Connection connection = DBConnection.getInstance();
    public static String LIST_ITEM = "INSERT INTO list_item (org_id, seller_id, asset_id, quantity," +
            "credit) VALUES (?,?,?,?,?)";
    public static String REMOVE_ITEM = "REMOVE FROM list_item WHERE id=?";
    public static String VIEW_MYLISTING = "SELECT i.type, o.name, u.username, l.quantity, l.credits FROM inventory i," +
            " organisation o, user u list_item l " +
            "WHERE l.org_id = o.org_id and l.seller_id = u.id and l.asset_id = i.id";
    public static String VIEW_ORGLISTING = "SELECT i.type, o.name, u.username, l.quantity, l.credits FROM inventory i,"+
            " organisation o, user u, list_item l WHERE l.org_id = o.org_id and l.asset_id = i.id";
    public static String BUY_ITEM_NEW = "INSERT INTO inventory (org_id, type, quantity) VALUES = (?,?,?)";
    public static String BUY_ITEM = "UPDATE inventory SET quantity = ? WHERE org_id = ? AND type = ?";
    public static String EDIT_CREDIT = "UPDATE organisations SET credits = ?";
    public static String ADD_TRANSACTION = "INSERT INTO transaction (org_id=?, seller_id=?, asset_id=?, quantity=?," +
            "credit=?,buyer_id=?)";

    private PreparedStatement listItem;
    private PreparedStatement removeItem;
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
}
