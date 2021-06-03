package test;

import client.adminUser;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class adminUserTest {
    private adminUser user1;

    @BeforeEach
    public void initAdmin(){
        user1 = new adminUser("Andre", 1);
    }

    @Test
    public void testAdminName(){
        assertEquals("Andre", user1.identity());
    }

//    @Test
//    public void testAdminID(){
//        assertEquals(1, user1.userID());
//    }
//
//    @Test
//    public void testIsAdmin(){
//        assertEquals(true, user1.isAdmin());
//    }
//
//    @Test
//    public void testTwoAdmin(){
//        adminUser u1 = new adminUser("Jayden",2, "123");
//        adminUser u2 = new adminUser("Alex", 3,"123");
//
//        assertEquals("Jayden", u1.identity());
//        assertEquals("Alex", u2.identity());
//
//        assertEquals(2, u1.userID());
//        assertEquals(3, u2.userID());
//
//        assertEquals(true, u1.isAdmin());
//        assertEquals(true, u2.isAdmin());
//    }
//
//    @Test
//    public void testHashPwd(){
//        String pwd = "asdsadasda";
//
//    }
}
