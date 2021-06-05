package test;

import client.normalUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class normalUserTest {
    private normalUser user1;

    // Integer userID, Integer org_id, String username, String first_name, String last_name, String hash_pwd,
    //                      Boolean isAdmin
    @BeforeEach
    public void initNormal(){
        user1 = new normalUser(1, 1,"username","davethegiantslayer69","Tom","B",false);
    }

    @Test
    public void testUserName(){
        assertEquals("Tom", user1.getFN());
    }
    @Test
    public void testUserID(){
        assertEquals(1, user1.getID());
    }

    @Test
    public void testIsAdmin(){
        assertEquals(false, user1.isAdmin());
    }

    @Test
    public void testOrgID(){
        assertEquals(1, user1.getOrgID());
    }

    @Test
    public void testTwoUser(){

    }
}
