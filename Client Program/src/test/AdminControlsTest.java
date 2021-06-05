package test;

import client.AdminControls;
import client.normalUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class AdminControlsTest {
    private AdminControls testing = new AdminControls();
    private normalUser user1 = new normalUser(1,"username","davethegiantslayer69",
            "Tom","B",false);


    @Test
    public void testAdminadduser(){
        testing.addUser(user1);
        assertEquals(user1.getFN(),testing.getUser(user1.getUser()).getFN());
    }

    @Test
    public void testduplicate() {
        assertEquals(false, testing.addUser(user1));
    }
}
