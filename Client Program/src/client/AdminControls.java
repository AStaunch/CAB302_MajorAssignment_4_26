package client;

import JDBC.DBConnection;

import java.sql.*;

public class AdminControls {
    private Connection connection = DBConnection.getInstance();
    public static String INSERT_USER = "INSERT INTO user (org_id, username, first_name, last_name, hash_pwd," +
            "is_admin) VALUES (?,?,?,?,?,?)";
    public static String REMOVE_USER = "DELETE FROM user WHERE username=?";
    public static String LIST_USERS = "SELECT * FROM user";
    public static String GET_USER = "SELECT * FROM user WHERE username=?";
    public static String MODIFY_USER = "UPDATE user SET org_id=?, username=?, first_name=?, last_name=?," +
            "hash_pwd=?, is_admin=? WHERE id=? ";
    public static String INSERT_ORG = "INSERT into organisation (org_name, description, credits) VALUES " +
            "(?,?,?)";
    public static String REMOVE_ORG = "DELETE * FROM organisation WHERE name=?";
    public static String GET_ORG = "SELECT * FROM organisation WHERE name=?";
    public static String LIST_ORG = "SELECT * FROM organisation";

    private PreparedStatement addUser;
    private PreparedStatement removeUser;
    private PreparedStatement listUsers;// do last
    private PreparedStatement getUser;
    private PreparedStatement modifyUser;
    private PreparedStatement addOrg;
    private PreparedStatement removeOrg;
    private PreparedStatement getOrg;
    private PreparedStatement listOrg;

    public AdminControls() {
        try {
            Statement st = this.connection.createStatement();
            this.addUser = this.connection.prepareStatement(INSERT_USER);
            this.removeUser = this.connection.prepareStatement(REMOVE_USER);
            this.listUsers = this.connection.prepareStatement(LIST_USERS);
            this.getUser = this.connection.prepareStatement(GET_USER);
            this.modifyUser = this.connection.prepareStatement(MODIFY_USER);
            this.addOrg = this.connection.prepareStatement(INSERT_ORG);
            this.removeOrg = this.connection.prepareStatement(REMOVE_ORG);
            this.getOrg= this.connection.prepareStatement(GET_ORG);
            this.listOrg = this.connection.prepareStatement(LIST_ORG);

        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }
    public void addOrg(orgUnit o) {
        try {
            this.addOrg.setString(1,o.getName());
            this.addOrg.setInt(2,o.getCredits());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeOrg(String name) {
        try {
            this.removeOrg.setString(1,name);
            this.removeOrg.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public orgUnit getOrg(String name) {
        orgUnit o = new orgUnit();
        ResultSet rs =  null;

        try{
            this.getUser.setString(1, name);
            rs = this.getUser.executeQuery();
            rs.next();
            o.setID(rs.getInt("id"));
            o.setName(rs.getString("name"));
            o.setCredit(rs.getInt("credits"));

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return o;
    }

    public boolean addUser(normalUser u) {
        try {
            if (this.getUser(u.getUser()) == null){
                this.addUser.setInt(1, u.getOrgID());
                this.addUser.setString(2, u.getUser());
                this.addUser.setString(3, u.getFN());
                this.addUser.setString(4, u.getLN());
                this.addUser.setString(5, u.getHash());
                this.addUser.setBoolean(6,u.isAdmin());
                this.addUser.execute();
                return true;
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public normalUser getUser(String username) {
        normalUser u = new normalUser();
        ResultSet rs =  null;

        try{

            this.getUser.setString(1, username);
            rs = this.getUser.executeQuery();
            rs.next();
            u.setID(rs.getInt("id"));
            u.setOrg_id(rs.getInt("org_id"));
            u.setUser(rs.getString("username"));
            u.setFN(rs.getString("first_name"));
            u.setLN(rs.getString("last_name"));
            u.setHash(rs.getString("hash_pwd"));
            u.setAdmin(rs.getBoolean("is_admin"));
            return u;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean removeUser(String username) {
        try {
            if (this.getUser(username) != null) {
                this.removeUser.setString(1, username);
                this.removeUser.executeUpdate();
                return true;
            }
        }
        catch (SQLException e) {
            return false;
        }
        return  false;
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
