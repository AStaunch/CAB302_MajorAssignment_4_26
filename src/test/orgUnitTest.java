package test;

import client.orgUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class orgUnitTest {
    orgUnit org;

    @BeforeEach
    public void initOrg(){
        org = new orgUnit(1,"Group1", 0.0);
    }

    @Test
    public void testOrgID(){
        assertEquals(1, org.orgID());
    }

    @Test
    public void testOrgName(){
        assertEquals("Group1", org.orgName());
    }

    @Test
    public void testOrgCredit(){
        assertEquals(0.0, org.orgCredit());
    }

    @Test
    public void testAddCredit(){
        org.addCredit(100.0);
        assertEquals(100.0, org.orgCredit());
    }

    @Test
    public void testRemoveCredit(){
        org.removeCredit(100.0);
        assertEquals(0, org.orgCredit());
    }

}
