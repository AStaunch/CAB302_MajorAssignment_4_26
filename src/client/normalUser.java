package client;

import java.util.Date;

/**
 * A class for creating a normal user object
 */
public class normalUser extends adminUser {
    private Integer userID;
    private Integer org_id;
    private String username;
    private String first_name;
    private String last_name;
    private String hash_pwd;
    private Date birth_date;
    private Boolean isAdmin;

    /**
     *
     */
    public normalUser() {
    }

    /**
     * Creates user object
     *
     * @param userID
     * @param org_id
     * @param username
     * @param first_name
     * @param last_name
     * @param hash_pwd
     * @param birth_date
     * @param isAdmin
     */
    public normalUser(Integer userID, Integer org_id, String username, String first_name, String last_name, String hash_pwd,
                      Date birth_date, Boolean isAdmin) {
        this.userID = userID;
        this.org_id = org_id;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.hash_pwd = hash_pwd;
        this.birth_date = birth_date;
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

    /** gets users birthdate
     *
     * @return
     */
    @Override
    public Date getBday() {return this.birth_date;}

    /** sets new user birthdate
     *
     * @param bday
     */
    @Override
    public void setBday(Date bday) {this.birth_date = bday;}

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
    public Integer getOrgID() {return this.org_id;}

    /** sets a new org for a user
     *
     * @param id
     */
    public void setOrg_id(Integer id) {this.org_id = id;}
}