package client;

import JDBC.DBConnection;

import java.sql.Connection;

public class UserControls {
    private Connection connection = DBConnection.getInstance();
    public static String LIST_ITEM;
    public static String REMOVE_ITEM;
    public static String VIEW_MYLISTING;
    public static String VIEW_ORGLISTING;
    public static String BUY_ITEM;
}
