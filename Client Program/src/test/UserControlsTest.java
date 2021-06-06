package test;

import client.AdminControls;
import client.UserControls;
import client.normalUser;
import client.orgUnit;
import common.assetUnit;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserControlsTest {
    UserControls uc = new UserControls();
    AdminControls ac = new AdminControls();


    @Test
    void listAsset() {
        List<assetUnit> result = uc.viewListing(true);
        //assertEquals(false,result.isEmpty());
    }

    @Test
    void listOrgListings(){
        orgUnit information = ac.getOrgByID(1);
        //assertEquals(false,uc.listOrgListings(information).isEmpty());
    }

    @Test
    void generalBuy() {
        //assetUnit unit = new assetUnit();

    }

    @Test
    void buyItem() {
    }

    @Test
    void buyNewItem() {
        // asset unit, normal user, amount
        normalUser user1 = ac.getUser("Alex");
        assetUnit unit_asset = uc.getItem(2); // CPU HOURS
        int Amount = 50; // Amount to buy
        uc.buyNewItem(unit_asset,user1,Amount);

    }


    @Test
    void addTransaction() {
        // Buy new items proves that this works
    }

}