package client;

/**
 * A class for creating organisation unit as object
 */
public class orgUnit implements org {

    private Double orgCredit;
    private String orgName;
    private Integer orgID;

    public orgUnit() {}


    /**
     * Create an instance of orgUnit
     *
     * @param orgID ID of the organisation
     * @param orgName Name of the organisation
     * @param orgCredit Credit that the organisation have
     */
    public orgUnit(Integer orgID, String orgName, Double orgCredit) {
        this.orgID = orgID;
        this.orgName = orgName;
        this.orgCredit = orgCredit;
    }

    public orgUnit(String orgName, Double orgCredit) {
        this.orgName = orgName;
        this.orgCredit = orgCredit;
    }

    /**
     * Adds amount to the credit that the organisation owns
     *
     * @param amount that will be added to the current credit
     * @return credit after adding amount
     */
    @Override
    public Double addCredit(Double amount) {
        orgCredit += amount;
        return orgCredit;
    }

    /**
     * Subtract amount to the credit that the organisation owns
     *
     * @param amount that will be subtracted to the current credit
     * @return credit after subtracting amount
     */
    @Override
    public Double removeCredit(Double amount) {
        orgCredit -= amount;
        return orgCredit;
    }

    /**
     * Get method for the credit that the organisation owns
     *
     * @return credit of the organisation
     */
    @Override
    public Double orgCredit() {
        return orgCredit;
    }

    /**
     * Get method for the name of the organisation
     *
     * @return name of the organisation
     */
    @Override
    public String orgName() {
        return orgName;
    }

    /**
     * Get method for the ID of the organisation
     *
     * @return ID of the organisation
     */
    @Override
    public Integer orgID() {
        return orgID;
    }
}