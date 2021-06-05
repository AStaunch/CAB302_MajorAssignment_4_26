package common;

public class InventoryAsset {

    private Integer assetID;
    private Integer org_ID;
    private enum Type {
        CPU_Hours
    }
    private String value;
    private Integer quantity;

    public Integer assetID() {
        return this.assetID;
    }
}
