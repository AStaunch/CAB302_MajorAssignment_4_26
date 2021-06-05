package test;

import client.adminUser;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class adminUserTest {
    private adminUser user1;

    // Integer userID, String username, String first_name, String last_name, String hash_pwd,
    //Date birth_date, Boolean isAdmin
    @BeforeEach
    public void initAdmin(){
        user1 = new adminUser(1, "Admin", "Firstname", "Lastname", "hash_pwd", true);
    }

    @Test
    public void testAdminName(){
        user1 = new adminUser(1, "Admin", "Firstname", "Lastname", "hash_pwd", true);
        assertEquals("Firstname", user1.getFN());
        assertEquals("Lastname", user1.getLN());
        assertEquals("Firstname", user1.getFN());
        assertEquals("Firstname", user1.getFN());
        assertEquals("Firstname", user1.getFN());

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
