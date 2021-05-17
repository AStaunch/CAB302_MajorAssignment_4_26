package test;

import client.normalUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class normalUserTest {
    normalUser user1;

    @BeforeEach
    public void initNormal(){
        user1 = new normalUser("Tom", 1,1);
    }

    @Test
    public void testUserName(){
        assertEquals("Tom", user1.identity());
    }
    @Test
    public void testUserID(){
        assertEquals(1, user1.userID());
    }

    @Test
    public void testIsAdmin(){
        assertEquals(false, user1.isAdmin());
    }

    @Test
    public void testOrgID(){
        assertEquals(1, user1.orgID());
    }

    @Test
    public void testTwoUser(){
        normalUser u1 = new normalUser("Jayden",2,1);
        normalUser u2 = new normalUser("Alex", 3,1);

        assertEquals("Jayden", u1.identity());
        assertEquals("Alex", u2.identity());

        assertEquals(2, u1.userID());
        assertEquals(3, u2.userID());

        assertEquals(1, u1.orgID());
        assertEquals(1, u2.orgID());

        assertEquals(false, u1.isAdmin());
        assertEquals(false, u2.isAdmin());
    }
}
