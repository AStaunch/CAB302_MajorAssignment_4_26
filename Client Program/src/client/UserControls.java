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
    public static String BUY_ITEM_NEW = "INSERT INTO";
    public static String BUY_ITEM = "UPDATE";
    public static String EDIT_CREDIT = "UPDATE organisations SET credits = ? WHERE  ";

    private PreparedStatement lisItem;
    private PreparedStatement removeItem;
    private PreparedStatement viewListing;
    private PreparedStatement viewOrgListing;

    public UserControls() {
        try {
            Statement st = this.connection.createStatement();
            this.lisItem = this.connection.prepareStatement(LIST_ITEM);
            this.removeItem = this.connection.prepareStatement(REMOVE_ITEM);
            this.viewListing = this.connection.prepareStatement(VIEW_MYLISTING);
            this.viewOrgListing = this.connection.prepareStatement(VIEW_ORGLISTING);


        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }
}
