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
    public static String VIEW_MYLISTING = "SELECT * FROM list_item where seller_id = ?";
    public static String VIEW_ORGLISTING = "SELECT * FROM list_item where org_id = ?";
    public static String BUY_ITEM_NEW = "INSERT INTO";
    public static String BUY_ITEM = "UPDATE";
    public static String EDIT_CREDIT = "UPDATE organisations SET credits = ? WHERE  ";

    private PreparedStatement removeItem;
    private PreparedStatement viewListing;
    private PreparedStatement viewOrgListing;

    public UserControls() {
        try {
            Statement st = this.connection.createStatement();
            this.removeItem = this.connection.prepareStatement(REMOVE_ITEM);
            this.viewListing = this.connection.prepareStatement(VIEW_MYLISTING);
            this.viewOrgListing = this.connection.prepareStatement(VIEW_ORGLISTING);


        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }
}
