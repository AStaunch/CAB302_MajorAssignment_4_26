package client;

import java.sql.*;

import JDBC.DBConnection;

public class AdminControls {
    private Connection connection = DBConnection.getInstance();
    public static String INSERT_USER = "INSERT INTO user (org_id, username, first_name, last_name, hash_pwd," +
            "is_admin) VALUES (?,?,?,?,?,?)";
    public static String REMOVE_USER = "DELETE * FROM user WHERE id=?";
    public static String LIST_USERS = "SELECT * FROM user";
    public static String GET_USER = "SELECT * FROM user WHERE id=?";
    public static String MODIFY_USER = "UPDATE user SET (org_id=?, username=?, first_name=?, last_name=?" +
            "hash_pwd=?, is_admin=?) WHERE id=? ";

    private PreparedStatement addUser;
    private PreparedStatement removeUser;
    private PreparedStatement listUsers;
    private PreparedStatement getUser;
    private PreparedStatement modifyUser;

    public AdminControls() {
        try {
            Statement st = this.connection.createStatement();
            this.addUser = this.connection.prepareStatement(INSERT_USER);
            this.removeUser = this.connection.prepareStatement(REMOVE_USER);
            this.listUsers = this.connection.prepareStatement(LIST_USERS);
            this.getUser = this.connection.prepareStatement(GET_USER);
            this.modifyUser = this.connection.prepareStatement(MODIFY_USER);

        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    public void addUser(normalUser u) {
        try {
            this.addUser.setInt(1, u.getOrgID());
            this.addUser.setString(2, u.getUser());
            this.addUser.setString(3, u.getFN());
            this.addUser.setString(4, u.getLN());
            this.addUser.setString(5, u.getHash());
            this.addUser.setBoolean(6,u.isAdmin());
            this.addUser.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public normalUser getUser(int id) {
        normalUser u = new normalUser();
        ResultSet rs =  null;

        try{
            this.getUser.setInt(1, id);
            rs = this.getUser.executeQuery();
            rs.next();
            u.setID(rs.getInt("id"));
            u.setOrg_id(rs.getInt("org_id"));
            u.setUser(rs.getString("username"));
            u.setFN(rs.getString("first_name"));
            u.setLN(rs.getString("last_name"));
            u.setHash(rs.getString("hash_pwd"));
            u.setAdmin(rs.getBoolean("is_admin"));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return u;
    }

    public void removeUser(Integer id) {
        try {
            this.removeUser.setInt(1,id);
            this.removeUser.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifyUser(user u) {
        try {
            this.modifyUser.setInt(1,u.getOrgID());
            this.modifyUser.setString(2,u.getUser());
            this.modifyUser.setString(3,u.getFN());
            this.modifyUser.setString(4,u.getLN());
            this.modifyUser.setString(5,u.getHash());
            this.modifyUser.setBoolean(6, u.isAdmin());
            this.modifyUser.setInt(7,u.getID());
            this.modifyUser.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
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
