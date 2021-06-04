package test;

import common.assetUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class assetUnitTest {
    private assetUnit asset1;

    @BeforeEach
    public void initAsset(){
        asset1 = new assetUnit("CPU", 1, 100.00);
    }

    @Test
    public void testAssetName(){
        assertEquals("CPU", asset1.assetName());
    }

    @Test
    public void testAssetID(){
        assertEquals(1, asset1.assetID());
    }

    @Test
    public void testAssetPrice(){
        assertEquals(100.00, asset1.assetPrice());
    }

    @Test
    public void testTwoAsset(){
        assetUnit a1 = new assetUnit("Monitor", 2, 300.00);
        assetUnit a2 = new assetUnit("Graphics Card", 3, 500.00);

        assertEquals("Monitor", a1.assetName());
        assertEquals("Graphics Card", a2.assetName());

        assertEquals(2, a1.assetID());
        assertEquals(3, a2.assetID());

        assertEquals(300.00, a1.assetPrice());
        assertEquals(500.00, a2.assetPrice());
    }
}
