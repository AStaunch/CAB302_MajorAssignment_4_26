package test;

import client.UserControls;
import common.assetUnit;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserControlsTest {
    UserControls uc = new UserControls();


    @Test
    void listAsset() {
        List<assetUnit> result = uc.listAsset(true);
    }

    @Test
    void generalBuy() {

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