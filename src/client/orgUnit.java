package client;

/**
 * A class for creating organisation unit as object
 */
public class orgUnit implements org {

    private Integer orgID;
    private String orgName;
    private Double orgCredit;

    public orgUnit(){}

    /**
     * Create an instance of orgUnit
     *
     * @param orgID ID of the organisation
     * @param orgName Name of the organisation
     * @param orgCredit Credit that the organisation have
     */
    public orgUnit(Integer orgID, String orgName, Double orgCredit) {
    }


    /**
     * Adds amount to the credit that the organisation owns
     *
     * @param amount that will be added to the current credit
     * @return credit after adding amount
     */
    @Override
    public Boolean addCredit(Double amount) {
        this.orgCredit = this.orgCredit + amount;
        return true;
    }

    /**
     * Subtract amount to the credit that the organisation owns
     *
     * @param amount that will be subtracted to the current credit
     * @return credit after subtracting amount
     */
    @Override
    public Boolean removeCredit(Double amount) {
        if (amount > this.orgCredit) {
            return false;
        }
        else {
            this.orgCredit = this.orgCredit - amount;
            return true;
        }
    }

    /**
     * Get method for the credit that the organisation owns
     *
     * @return credit of the organisation
     */
    @Override
    public Double orgCredit() {
        return this.orgCredit;
    }

    /**
     * Get method for the name of the organisation
     *
     * @return name of the organisation
     */
    @Override
    public String orgName() {
        return this.orgName;
    }

    /**
     * Get method for the ID of the organisation
     *
     * @return ID of the organisation
     */
    @Override
    public Integer orgID() {
        return this.orgID;
    }
}
