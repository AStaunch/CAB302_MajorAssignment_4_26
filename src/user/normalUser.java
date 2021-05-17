package user;

/**
 * A class for creating a normal user object
 */
public class normalUser extends adminUser{
    private String name;
    private Integer userID;
    private Boolean isAdmin;
    private Integer orgID;

    /**
     * Create an instance of normalUser
     *
     * @param name Name of the user
     * @param userID ID of the user
     * @param orgID ID of the organisation is part of
     */
    public normalUser(String name, Integer userID, Integer orgID){
        super(name, userID);
    }

    /**
     * Get method for the ID of the organisation the user is part of
     *
     * @return ID of the user
     */
    public Integer orgID(){
        return 0;
    }

    /**
     * Get method for the identity of the user
     *
     * @return name of the user
     */
    @Override
    public String identity() {
        return null;
    }

    /**
     * Get method for the ID of the user
     *
     * @return ID of the user
     */
    @Override
    public Integer userID() {
        return 0;
    }

    /**
     * Get method for the status of the user.
     * For normal users this will be false.
     *
     * @return status of the user
     */
    @Override
    public Boolean isAdmin() {
        return null;
    }
}
