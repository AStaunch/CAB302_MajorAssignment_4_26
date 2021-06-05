package client;

/**
 * A Class for creating admin user as objects
 */

public class adminUser implements user {
    private Integer userID;
    private Integer org_id;
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
     * @param userID
     * @param username
     * @param first_name
     * @param last_name
     * @param hash_pwd
     * @param isAdmin
     */
    public adminUser(Integer userID, String username, String first_name, String last_name, String hash_pwd,
                     Boolean isAdmin) {
        this.userID = userID;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.hash_pwd = hash_pwd;
        this.isAdmin = isAdmin;

    }

    public adminUser(String username, String first_name, String last_name, String hash_pwd,
                     Boolean isAdmin) {
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.hash_pwd = hash_pwd;
        this.isAdmin = isAdmin;

    }

    /** Gets the user id
     *
     * @return
     */
    @Override
    public Integer getID() {return this.userID;}

    /**
     *
     * @param id
     */
    @Override
    public void setID(Integer id) {this.userID = id;}

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

    /** Checks if user is admin
     *
     * @return
     */
    @Override
    public Boolean isAdmin() {return this.isAdmin;}

    /** gets the org id of user
     *
     * @return
     */
    @Override
    public Integer getOrgID() {return this.org_id;}

    /** sets a new org for a user
     *
     * @param id
     */
    @Override
    public void setOrg_id(Integer id) {this.org_id = id;}

    @Override
    public void setAdmin(Boolean admin) {
        this.isAdmin = admin;
    }
    @Override
    public String ToString(int id) {
        String s = String.format("Username: %s, Full name: %s %s from org with id: %d",
                this.username, this.first_name, this.last_name);

        return s;
    }



}