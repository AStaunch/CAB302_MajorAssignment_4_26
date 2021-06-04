package client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import JDBC.DBConnection;

public class AdminControls {
    private Connection connection = DBConnection.getInstance();
    public static String INSERT_USER = "INSERT INTO user (org_id, username, first_name, last_name, hash_pwd," +
            "birth_date, is_admin) VALUES (?,?,?,?,?,?,?)";
    public static String REMOVE_USER = "DELETE * FROM user WHERE id=?";
    public static String LIST_USERS = "SELECT * FROM users";

    private PreparedStatement addUser;
    private PreparedStatement removeUser;
    private PreparedStatement listUsers;

    public AdminControls() {
        try {
            Statement st = this.connection.createStatement();
            this.addUser = this.connection.prepareStatement(INSERT_USER);
            this.removeUser = this.connection.prepareStatement(REMOVE_USER);
            this.listUsers = this.connection.prepareStatement(LIST_USERS);

        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }


    public void close() {
        try {
            this.connection.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }


}
