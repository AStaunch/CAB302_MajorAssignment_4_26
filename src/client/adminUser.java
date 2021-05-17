package client;

/**
 * A Class for creating admin user as objects
 */

public class adminUser implements user {
    private String name;
    private Integer userID;
    private Boolean isAdmin;

    /**
     * Create an instance of adminUser
     *
     * @param name Name of the user
     * @param userID ID of the user
     */
    public adminUser(String name, Integer userID){

    }

    /**
     * Get method for the ID of the user
     *
     * @return the ID of the user
     */
    @Override
    public Integer userID() {
        return 0;
    }

    /**
     * Get method for the status of the user
     * For admin user this will true
     * @return the status of the user
     */
    @Override
    public Boolean isAdmin() {
        return null;
    }

    /**
     * Get method for the name of the user
     *
     * @return the name of the user
     */
    @Override
    public String identity() {
        return null;
    }
}
