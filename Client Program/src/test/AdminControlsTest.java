package test;

import client.AdminControls;
import client.normalUser;
import common.InventoryAsset;
import org.junit.jupiter.api.Test;
import client.orgUnit;


import static org.junit.jupiter.api.Assertions.assertEquals;
public class AdminControlsTest {
    private AdminControls testing = new AdminControls();
    private normalUser user1 = new normalUser(1,"Username","davethegiantslayer69",
            "Tom",testing.encode("Fish"),false);

    @Test
    public void testduplicate() {
        assertEquals(false, testing.addUser(user1));
    }

    @Test
    public void login() {
        // Testing if the expected password hash is correct
        assertEquals(testing.encode("Fish"), user1.getHash());
        user1.setHash("");
        user1.setUser("Not added");
        assertEquals(false, testing.addUser(user1));
        user1.setUser("");
        user1.setHash(testing.encode("Fish"));
        assertEquals(false, testing.addUser(user1));
        user1.setUser("Username");
    }

    @Test
    public void testedditcomand(){
        testing.addUser(user1);
        normalUser testuser = new normalUser();
        testuser = testing.getUser(user1.getUser());
        testuser.setFN("Ragnor the destroyer");
        testing.modifyUser(testuser);
        assertEquals("Ragnor the destroyer", testing.getUser(user1.getUser()).getFN());
    }

    @Test
    public void testremoveuser(){
        assertEquals(true,testing.removeUser(user1.getUser()));
        assertEquals(false,testing.removeUser(user1.getUser()));
        assertEquals(false,testing.removeUser("I am not in the database"));


    }

    @Test
    public void addInvasset(){
        InventoryAsset assettest = new InventoryAsset();
        assettest.setQTY(100);
        assettest.setType("Testing");
        assettest.setOrg(2);
        testing.addInvAsset(assettest);
    }

    @Test
    public void listAsset() {
        String[] a = testing.listInvAsset();
        for (int i = 0; i < a.length; i ++ ) {
            System.out.println(a[i]);
        }
    }

    @Test
    public void getOrg() {
        orgUnit o = new orgUnit();
        o = testing.getOrg("Alexs org");
    }

    @Test
    public void getOrg1() {
        orgUnit o = new orgUnit();
        o = testing.getOrgByID(1);
        System.out.println(o.getName());
    }

    @Test void addOrg() {
        orgUnit o = new orgUnit("Stanky", 100);
        testing.addOrg(o);
    }
}
