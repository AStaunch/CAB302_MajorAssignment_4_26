package client;

public interface org {
    String orgName();
    Integer orgID();
    Double orgCredit();
    Boolean addCredit(Double amount);
    Boolean removeCredit(Double amount);
}
