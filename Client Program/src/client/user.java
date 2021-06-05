package client;

import java.util.Date;

public interface user {
    Integer getID();
    void setID(Integer id);
    String getUser();
    void setUser(String username);
    String getFN();
    void setFN( String FN);
    String getLN();
    void setLN( String LN);
    String getHash();
    void setHash( String Hash);
    Integer getOrgID();
    void setOrg_id(Integer id);
    void setAdmin(Boolean admin);
    String ToString(int id);
    Boolean isAdmin();

}
