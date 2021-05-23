package common;

/**
 * A class for creating asset unit object
 */
public class assetUnit implements asset{
    private String assetName;
    private Integer assetID;
    private Double assetPrice;

    /**
     * Create an instance of assetUnit
     *
     * @param assetName Name of the asset
     * @param assetID ID of the asset
     * @param assetPrice Price of the asset
     */
    public assetUnit(String assetName, Integer assetID, Double assetPrice){
        this.assetName = assetName;
        this.assetID = assetID;
        this.assetPrice = assetPrice;
    }

    /**
     * This method adds the asset to the database
     *
     * @param assetName Name of the asset
     * @param assetID ID of the common asset
     * @param assetPrice Price of the asset
     * @return True if successful, False otherwise
     */
    public Boolean addAsset(String assetName, Integer assetID, Double assetPrice){
        return null;
    }

    /**
     *  This method remove asset from the database
     *
     * @param assetID ID of the asset
     * @return True if successful, False otherwise
     */
    public Boolean removeAsset(Integer assetID){
        return null;
    }

    /**
     * Get method for the name of the asset
     *
     * @return name of the asset
     */
    @Override
    public String assetName() {
        return assetName;
    }

    /**
     * Get method for price of the asset
     *
     * @return Price of the asset
     */
    @Override
    public Double assetPrice() {
        return assetPrice;
    }

    /**
     * Get method for the ID of the asset
     *
     * @return ID of the asset
     */
    @Override
    public Integer assetID() {
        return assetID;
    }
}
