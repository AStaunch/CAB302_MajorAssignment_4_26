package client;

import java.util.Date;

/**
 * A Class for creating admin user as objects
 */

public class adminUser implements user {
    private Integer userID;
    private String username;
    private String first_name;
    private String last_name;
    private String hash_pwd;
    private Boolean isAdmin;

    /**
     * Creates instance of admin user with no parameters
     */
    public adminUser() {
    }

    /**
     * @param userID - The user ID of the admin
     * @param username - Admins username
     * @param first_name - The first name of the admin
     * @param last_name - The last name of the admin
     * @param hash_pwd - The admins hashed password
     * @param isAdmin - If the admin user is an admin (Default true)
     */
    public adminUser(Integer userID, String username, String first_name, String last_name, String hash_pwd,
                     Boolean isAdmin) {
        this.userID = userID;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.hash_pwd = hash_pwd;
        //this.birth_date = birth_date;
        this.isAdmin = isAdmin;

    }

    /** Gets the user id
     *
     * @return
     */
    @Override
    public Integer getID() {return this.userID;}

    /** Gets username
     *
     * @return
     */
    @Override
    public String getUser() {return this.username;}

    /** Sets new username
     *
     * @param username
     */
    @Override
    public void setUser(String username) {this.username = username;}

    /** Gets firstname
     *
     * @return
     */
    @Override
    public String getFN() {return this.first_name;}

    /** Sets new firstname
     *
     * @param FN
     */
    @Override
    public void setFN(String FN) {this.first_name = FN;}

    /** Gets lastname
     *
     * @return
     */
    @Override
    public String getLN() {return this.last_name;}

    /** Sets new lastname
     *
     * @param LN
     */
    @Override
    public void setLN(String LN) {this.last_name = LN;}

    /** gets the hashed pwd
     *
     * @return
     */
    @Override
    public String getHash() {return this.hash_pwd;}

    /** Sets a new hashed password
     *
     * @param hash
     */
    @Override
    public void setHash(String hash) {this.hash_pwd = hash; }

    @Override
    public Boolean isAdmin() {return this.isAdmin;}


}