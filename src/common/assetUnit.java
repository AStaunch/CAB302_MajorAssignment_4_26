package common;

/**
 * A class for creating asset unit object
 */
public class assetUnit implements asset {
    private Integer assetID;
    private Integer org_ID;
    private Integer quantity;
    private enum Type {
        CPU_Hours
    }
    private Integer cost;

    public Integer assetID() {
        return this.assetID;
    }


}