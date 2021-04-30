package user;

public class normalUser extends adminUser{
    private String name;
    private Integer userID;
    private Boolean status;
    private Integer orgID;

    public normalUser(String name, Integer userID, Boolean status, Integer orgID){
        super(name, userID, status);
        this.orgID = orgID;
    }

    public Integer orgID(){
        return orgID;
    }

    @Override
    public String identity() {
        return name;
    }

    @Override
    public Integer userID() {
        return userID;
    }

    @Override
    public Boolean status() {
        return status;
    }
}
