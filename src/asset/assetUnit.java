package asset;

/**
 * A class for creating asset unit object
 */
public class assetUnit implements asset{
    private String assetName;
    private Integer assetID;

    /**
     * Create an instance of assetUnit
     *
     * @param assetName Name of the asset
     * @param assetID ID of the asset
     */
    public assetUnit(String assetName, Integer assetID){
    }

    /**
     * Get method for the name of the asset
     *
     * @return name of the asset
     */
    @Override
    public String assetName() {
        return null;
    }

    /**
     * Get method for the ID of the asset
     *
     * @return ID of the asset
     */
    @Override
    public Integer assetID() {
        return 0;
    }
}
