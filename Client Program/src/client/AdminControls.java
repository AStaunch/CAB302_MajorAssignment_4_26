package client;

import JDBC.DBConnection;
import common.InventoryAsset;
import common.assetUnit;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AdminControls {
    private Connection connection = DBConnection.getInstance();
    // User SQL
    public static String INSERT_USER = "INSERT INTO user (org_id, username, first_name, last_name, hash_pwd," +
            "is_admin) VALUES (?,?,?,?,?,?)";
    public static String REMOVE_USER = "DELETE FROM user WHERE username=?";
    public static String LIST_USERS = "SELECT * FROM user";
    public static String GET_USER = "SELECT * FROM user WHERE username=?";
    public static String MODIFY_USER = "UPDATE user SET org_id=?, username=?, first_name=?, last_name=?," +
            "hash_pwd=?, is_admin=? WHERE id=? ";

    // Org SQL
    public static String INSERT_ORG = "INSERT into organisation (name, credits) VALUES (?,?)";
    public static String REMOVE_ORG = "DELETE FROM organisation WHERE name=?";
    public static String GET_ORG = "SELECT * FROM organisation WHERE name=?";
    public static String GET_ORG1 = "SELECT * FROM organisation WHERE org_id=?";
    public static String LIST_ORG = "SELECT * FROM organisation";

    // Inventory SQL
    public static String ADD_INVASSET = "INSERT INTO inventory (org_id, type, quantity) VALUES = (?,?,?)";
    public static String REMOVE_INVASSET = "DELETE FROM inventory where id=?";
    public static String EDIT_INVASSET = "UPDATE inventory SET quantity=? where id=?";
    public static String GET_INVASSET = "SELECT * FROM inventory WHERE id=?";
    public static String GET_IA_BYTYPEORG = "SELECT * FROM inventory WHERE type=? AND org_id=?";
    public static String LIST_INVASSET = "SELECT type, quantity FROM inventory WHERE org_id";

    // Prepared statements for user SQL
    private PreparedStatement addUser;
    private PreparedStatement removeUser;
    private PreparedStatement listUsers;
    private PreparedStatement getUser;
    private PreparedStatement modifyUser;

    // Prepared statements for org SQL
    private PreparedStatement addOrg;
    private PreparedStatement removeOrg;
    private PreparedStatement getOrg;
    private PreparedStatement getOrg1;
    private PreparedStatement listOrg;

    // Prepared statements for inventory SQL
    private PreparedStatement add_invAsset;
    private PreparedStatement remove_invAsset;
    private PreparedStatement edit_invAsset;
    private PreparedStatement get_invAsset;
    private PreparedStatement get_invAssetTO;
    private PreparedStatement list_invAsset;

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
            this.getOrg1 = this.connection.prepareStatement(GET_ORG1);
            this.listOrg = this.connection.prepareStatement(LIST_ORG);
            this.add_invAsset = this.connection.prepareStatement(ADD_INVASSET);
            this.remove_invAsset = this.connection.prepareStatement(REMOVE_INVASSET);
            this.edit_invAsset = this.connection.prepareStatement(EDIT_INVASSET);
            this.list_invAsset = this.connection.prepareStatement(LIST_INVASSET);
            this.get_invAsset = this.connection.prepareStatement(GET_INVASSET);
            this.get_invAssetTO = this.connection.prepareStatement(GET_IA_BYTYPEORG);

        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }

    /** Adds a new inventory asset into the DB
     *
     * @param i Takes in a invenotry asset type
     */
    public void addInvAsset(InventoryAsset i) {
        try {
            this.add_invAsset.setInt(1,i.getOrg());
            this.add_invAsset.setString(2,i.getType());
            this.add_invAsset.setInt(3,i.getQTY());
            this.add_invAsset.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Removes an asset from the inventory
     *
     * @param id, takes ID to identify
     */
    public void removeInvAsset(Integer id) {
        try {
            this.remove_invAsset.setInt(1,id);
            this.remove_invAsset.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** from an invAsset, modifies the current values
     *
     * @param i takes invAsset type
     */
    public void editInv(InventoryAsset i) {
        try {
            this.edit_invAsset.setInt(1,i.getQTY());
            this.edit_invAsset.setInt(1,i.getID());
            this.modifyUser.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** Gets an inv asset using the id
     *
     * @param id
     * @return an inv asset
     */
    public InventoryAsset getInvAsset(Integer id) {
        InventoryAsset i = new InventoryAsset();
        ResultSet rs =  null;

        try{
            this.get_invAsset.setInt(1, id);
            rs = this.get_invAsset.executeQuery();
            rs.next();
            i.setID(rs.getInt("id"));
            i.setOrg(rs.getInt("org_id"));
            i.setType(rs.getString("type"));
            i.setQTY(rs.getInt("quantity"));

        }
        catch (SQLException e){
           return null;
        }
        return i;
    }

    /** finds inventory asset knowing org id and the type
     *
     * @param type as seen in db
     * @param org_id the org number
     * @return an inv asset
     */
    public InventoryAsset getInvAssetTO(String type, Integer org_id) {
        InventoryAsset i = new InventoryAsset();
        ResultSet rs =  null;

        try{
            this.get_invAssetTO.setString(1, type);
            this.get_invAssetTO.setInt(1, org_id);
            rs = this.get_invAssetTO.executeQuery();
            rs.next();
            i.setID(rs.getInt("id"));
            i.setOrg(rs.getInt("org_id"));
            i.setType(rs.getString("type"));
            i.setQTY(rs.getInt("quantity"));

        }
        catch (SQLException e){
            return null;
        }
        return i;
    }

    /** Gets an array of inventory assets for gui applications
     *
     * @return  an array of inventory assets
     */
    public String[] listInvAsset() {
        Set<String> ia = new TreeSet();
        ResultSet rs = null;

        try {
            rs = this.list_invAsset.executeQuery();

            while(rs.next()) {
                String temp = rs.getString(1) + ", " + String.valueOf(rs.getInt(2));
                ia.add(temp);
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        String[] iaArr = new String[ia.size()];
        ia.toArray(iaArr);

        return iaArr;
    }

    /** adding a new org
     *
     * @param o, takes in a constructed org object
     */
    public boolean addOrg(orgUnit o) {

        try {
            this.addOrg.setString(1,o.getName());
            this.addOrg.setInt(2,o.getCredits());
            this.addOrg.execute();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** takes in the name of an org used in GUI to remove an org
     *
     * @param name, the org name
     */
    public void removeOrg(String name) {
        try {
            this.removeOrg.setString(1,name);
            this.removeOrg.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** finds an org using its name
     *
     * @param name takes org name as param
     * @return returns an org with the name name
     */
    public orgUnit getOrg(String name) {
        orgUnit o = new orgUnit();
        ResultSet rs =  null;
        try{
            this.getOrg.setString(1, name);
            rs = this.getOrg.executeQuery();
            rs.next();
            o.setID(rs.getInt("org_id"));
            o.setName(rs.getString("name"));
            o.setCredit(rs.getInt("credits"));
            return o;

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /** find an org by its id (used by admin)
     *
     * @param id org id
     * @return an org with the id, id
     */
    public orgUnit getOrgByID(Integer id) {
        orgUnit o = new orgUnit();
        ResultSet rs =  null;
        try{
            this.getOrg1.setInt(1, id);
            rs = this.getOrg1.executeQuery();
            rs.next();
            o.setID(rs.getInt("org_id"));
            o.setName(rs.getString("name"));
            o.setCredit(rs.getInt("credits"));
            return o;

        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /** For GUI application, gets a list of names
     *
     * @return a string of org names for GUI
     */
    public String[] listOrg() {
        Set<String> org = new TreeSet();
        ResultSet rs = null;

        try {
            rs = this.listOrg.executeQuery();

            while(rs.next()) {
                org.add(rs.getString("name"));
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }
        if (org.contains("Admin")) {
            org.remove("Admin");
        }
        String[] orgArr = new String[org.size()];
        org.toArray(orgArr);


        return orgArr;
    }

    /** adds a new user to the db
     *
     * @param u takes object user
     * @return bool to see if successful or not
     */
    public boolean addUser(normalUser u) {
        try {
            if (this.getUser(u.getUser()) == null && u.getHash() != null && !u.getHash().equals("")
                && !u.getUser().equals(""))
            {
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

    /** getting a user with username username
     *
     * @param username, takes username
     * @return a user
     */
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

            return null;

        }
    }

    /** method to remove a user
     *
     * @param username, takes username
     * @return bool to see if it worked
     */
    public boolean removeUser(String username) {
        UserControls u = new UserControls();
        try {
            if (this.getUser(username) != null) {
                this.removeUser.setString(1, username);
                this.removeUser.executeUpdate();

                // removes any listings user previously had
                List<assetUnit> listAssetUnit = u.listMyListings(getUser(username));
                listAssetUnit.forEach(assetUnit -> {
                    u.removeItem(assetUnit.getID());
                } );


                return true;
            }
        }
        catch (SQLException e) {
            return false;
        }
        return  false;
    }

    /** method to modify a user as per GUI input
     *
     * @param u, new user object parse from gui
     */
    public void modifyUser(normalUser u) {
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

    /** gets a list of usernames
     *
     * @return a list of usernames
     */
    public String[] listUser() {
        Set<String> users = new TreeSet();
        ResultSet rs = null;

        try {
            rs = this.listUsers.executeQuery();

            while(rs.next()) {
                users.add(rs.getString("username"));
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        String[] userArr = new String[users.size()];
        users.toArray(userArr);

        return userArr;
    }

    /** password encrypting
     *
     * @param password
     * @return an encrypted password for the db
     */
    public String encode(String password){
        String generatedpassword = null;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedpassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedpassword;
    }

    public void close() {
        try {
            this.connection.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }


}
