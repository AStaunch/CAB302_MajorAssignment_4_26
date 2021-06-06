package common;

/**
 * A class for creating asset unit object, asset unit being a object that is listed, either buy or sell in the system.
 */
public class assetUnit implements asset {
    private Integer id;
    private Integer org_id;
    private Integer seller_id;
    private Integer asset_id;
    private Integer quantity;
    private Integer credits;
    private boolean bs;

    public assetUnit() {};

    /**
     * Constructs a new Listed Asset Object
     * @param id The Unique ID of the asset listing
     * @param org_id The organisation ID for the organisation the user belongs to
     * @param seller_id The user ID of the user listing the asset
     * @param asset_id The ID of the asset type being listed.
     * @param quantity The Quantity of the assets being listed.
     * @param credits The Price / Unit listed of the asset object
     * @param bs True if the listing is a sell order, False if it is a Buy Order
     */
    public assetUnit(Integer id, Integer org_id, Integer seller_id, Integer asset_id, Integer quantity,
                     Integer credits, boolean bs) {
        this.id = id;
        this.org_id = org_id;
        this.seller_id = seller_id;
        this.asset_id = asset_id;
        this.quantity = quantity;
        this.credits = credits;
        this.bs = bs;


    }

    /**
     * Constructs a new Listed Asset Object, without respect to the unique listing o the asset
     * @param org_id The organisation ID for the organisation the user belongs to
     * @param seller_id The user ID of the user listing the asset
     * @param asset_id The ID of the asset type being listed.
     * @param quantity The Quantity of the assets being listed.
     * @param credits The Price / Unit listed of the asset object
     * @param bs True if the listing is a sell order, False if it is a Buy Order
     */
    public assetUnit(Integer org_id, Integer seller_id, Integer asset_id, Integer quantity,
                     Integer credits, boolean bs) {
        this.org_id = org_id;
        this.seller_id = seller_id;
        this.asset_id = asset_id;
        this.quantity = quantity;
        this.credits = credits;
        this.bs = bs;
    }

    /**
     * Gets the ID of the Asset Listing
     * @return ID of the Asset Listing
     */
    @Override
    public Integer getID() {return this.id;}

    /**
     * Sets the ID of the Asset Listing
     * @param i Intended ID of the asset
     */
    @Override
    public void setID(Integer i) {
        this.id = i;
    }

    /**
     * Gets the ID of the organisation that owns list listing
     * @return ID of the organisation
     */
    @Override
    public Integer getOrg() {
        return this.org_id;
    }

    /**
     * Sets the ID of the organisation that owns list listing
     * @param i Intended ID of the organisation that owns list listing
     */
    @Override
    public void setOrg(Integer i) {
        this.org_id = i;
    }

    /**
     * Gets the ID of the user who created this listing
     * @return ID of the user who created this listing
     */
    public Integer getSeller() {
        return this.seller_id;
    }

    /**
     * Sets the ID of the user who created this listing
     * @param i Intended ID of the user who created this listing
     */
    public void setSeller(Integer i) {
        this.seller_id = i;
    }

    /**
     * Gets the ID of the Asset being listed
     * @return ID of the Asset being listed
     */
    public Integer getAsset() {
        return this.asset_id;
    }

    /**
     * Sets the ID of the Asset being listed
     * @param i Intended ID of the Asset being listed
     */
    public void setAsset(Integer i) {
        this.asset_id = i;
    }

    /**
     * Gets the Quantity of Assets in this listing
     * @return Quantity of Assets in this listing
     */
    @Override
    public Integer getQTY() {
        return this.quantity;
    }

    /**
     * Sets the Quantity of Assets in this listing
     * @param i Intended Quantity of Assets in this listing
     */
    @Override
    public void setQTY(Integer i) {
        this.quantity = i;
    }

    /**
     * Gets the Price / Unit of Assets in this listing
     * @return The Price / Unit of Assets in this listing
     */
    public Integer getCredits() {return this.credits;}

    /**
     * Sets the Price / Unit of Assets in this listing
     * @param i Intended Price / Unit of Assets in this listing
     */
    public void setCredits(Integer i) {this.credits = i;}


}