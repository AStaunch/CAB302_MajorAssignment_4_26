package client;

import java.sql.*;

import JDBC.DBConnection;

public class AdminControls {
    private Connection connection = DBConnection.getInstance();
    public static String INSERT_USER = "INSERT INTO user (org_id, username, first_name, last_name, hash_pwd," +
            "birth_date, is_admin) VALUES (?,?,?,?,?,?,?)";
    public static String REMOVE_USER = "DELETE * FROM user WHERE id=?";
    public static String LIST_USERS = "SELECT * FROM user";
    public static String GET_USER = "SELECT * FROM user WHERE id=?";

    private PreparedStatement addUser;
    private PreparedStatement removeUser;
    private PreparedStatement listUsers;
    private PreparedStatement getUser;

    public AdminControls() {
        try {
            Statement st = this.connection.createStatement();
            this.addUser = this.connection.prepareStatement(INSERT_USER);
            this.removeUser = this.connection.prepareStatement(REMOVE_USER);
            this.listUsers = this.connection.prepareStatement(LIST_USERS);
            this.getUser = this.connection.prepareStatement(GET_USER);

        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    public String getUser(int id) {
        user u = new normalUser();
        ResultSet rs =  null;

        try{
            this.getUser.setInt(1, id);
            rs = this.getUser.executeQuery();
            rs.next();
            //u.setOrg_id(rs.getInt("org_id"));
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return "test";
    }




    public void close() {
        try {
            this.connection.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }


}
