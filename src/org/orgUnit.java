package org;

public abstract class orgUnit implements org{

    private Double orgCredit;
    private String orgName;
    private Integer orgID;

    public orgUnit(Integer orgID, String orgName, Double orgCredit) {
        this.orgID = orgID;
        this.orgName = orgName;
        this.orgCredit = orgCredit;
    }

    @Override
    public Double addCredit(Double amount) {
        orgCredit = orgCredit + amount;
        return orgCredit;
    }

    @Override
    public Double removeCredit(Double amount) {
        orgCredit = orgCredit - amount;
        return orgCredit;
    }

    @Override
    public Double orgCredit() {
        return orgCredit;
    }

    @Override
    public String orgName() {
        return orgName;
    }

    @Override
    public Integer orgID() {
        return orgID;
    }
}
