package test;

import client.AdminControls;
import client.UserControls;
import client.orgUnit;
import common.assetUnit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

class UserControlsTest {
    UserControls uc = new UserControls();
    AdminControls ac = new AdminControls();


    @Test
    void listAsset() {
        List<assetUnit> result = uc.viewListing(true);
        assertEquals(false,result.isEmpty());
    }

    @Test
    void listOrgListings(){
        orgUnit information = ac.getOrgByID(1);
        assertEquals(false,uc.listOrgListings(information).isEmpty());
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
    }

    @Test
    void editCredit() {
    }

    @Test
    void addTransaction() {
    }

    @Test
    void listItem() {
    }

    @Test
    void removeItem() {
    }

    @Test
    void getItem() {
    }
}