package common;

/**
 * A class for creating asset unit object
 */
public class assetUnit implements asset {
    private Integer id;
    private Integer org_id;
    private Integer seller_id;
    private Integer asset_id;
    private Integer quantity;
    private Integer credits;

    public assetUnit() {};
    public assetUnit(Integer id, Integer org_id, Integer seller_id, Integer asset_id, Integer quantity,
                     Integer credits) {
    }
    public assetUnit(Integer org_id, Integer seller_id, Integer asset_id, Integer quantity,
                     Integer credits) {
    }

    @Override
    public Integer getID() {return this.id;}
    @Override
    public void setID(Integer i) {
        this.id = i;
    }
    @Override
    public Integer getOrg() {
        return this.org_id;
    }
    @Override
    public void setOrg(Integer i) {
        this.org_id = i;
    }

    public Integer getSeller() {
        return this.seller_id;
    }

    public void setSeller(Integer i) {
        this.seller_id = i;
    }

    public Integer getAsset() {
        return this.asset_id;
    }

    public void setAsset(Integer i) {
        this.asset_id = i;
    }
    @Override
    public Integer getQTY() {
        return this.quantity;
    }
    @Override
    public void setQTY(Integer i) {
        this.quantity = i;
    }

    public Integer getCredits() {return this.credits;}

    public void setCredits(Integer i) {this.credits = i;}


}