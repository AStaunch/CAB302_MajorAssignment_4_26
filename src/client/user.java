package client;

import java.util.Date;

public interface user {
    Integer getID();
    String getUser();
    void setUser(String username);
    String getFN();
    void setFN( String FN);
    String getLN();
    void setLN( String LN);
    String getHash();
    void setHash( String Hash);
    java.util.Date getBday();
    void setBday(Date bday);
    Boolean isAdmin();

}
