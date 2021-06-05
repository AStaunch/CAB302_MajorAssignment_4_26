package client;

/**
 * A class for creating organisation unit as object
 */
public class orgUnit implements org {
    private Integer orgID;
    private Integer orgCredit;
    private String orgName;

    public orgUnit() {}

    /**
     * Create an instance of orgUnit
     *
     * @param orgID ID of the organisation
     * @param orgName Name of the organisation
     * @param orgCredit Credit that the organisation have
     */
    public orgUnit(Integer orgID, String orgName, Integer orgCredit) {
        this.orgID = orgID;
        this.orgName = orgName;
        this.orgCredit = orgCredit;
    }

    public orgUnit(String orgName, Integer orgCredit) {
        this.orgName = orgName;
        this.orgCredit = orgCredit;
    }
    @Override
    public String getName() {
        return this.orgName;
    }
    @Override
    public void setName(String name) {
        this.orgName = name;
    }
    @Override
    public Integer getID() {
        return this.orgID;
    }
    @Override
    public void setID(Integer id) {
        this.orgID = id;
    }
    @Override
    public Integer getCredits() {
        return this.orgCredit;

    }
    @Override
    public void setCredit(Integer credit) {
        this.orgCredit = credit;
    }


}
