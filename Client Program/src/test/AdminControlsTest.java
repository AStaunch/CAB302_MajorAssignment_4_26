package test;

import client.AdminControls;
import client.normalUser;
import org.testng.annotations.Test;

public class AdminControlsTest {
    private AdminControls testing = new AdminControls();
    private normalUser user1;

    @Test
    public void testAdminName(){
        user1 = new normalUser(1,"username","davethegiantslayer69","Tom","B",false);
        testing.addUser(user1);
    }
}
