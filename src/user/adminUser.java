package user;

public class adminUser implements user{
    private String name;
    private Integer userID;
    private Boolean status;

    public adminUser(String name, Integer userID, Boolean status){
        this.name = name;
        this.userID = userID;
        this.status = status;
    }


    @Override
    public Integer userID() {
        return userID;
    }

    @Override
    public Boolean status() {
        return status;
    }

    @Override
    public String identity() {
        return name;
    }
}
