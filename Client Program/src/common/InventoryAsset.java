package common;

public class InventoryAsset implements asset {
    private Integer id;
    private Integer org_id;
    private String Type;

    private Integer quantity;

    public InventoryAsset() {};

    /**
     * Constructs a new Inventory Asset
     * @param id Unique ID of the Inventory Asset
     * @param org_id Organisation ID of the organisation that owns this Asset
     * @param type The Type of Asset this is
     * @param quantity The Quantity of this asset in the system
     */
    public InventoryAsset(Integer id, Integer org_id, String type, Integer quantity) {
        this.id = id;
        this.org_id = org_id;
        this.Type = type;
        this.quantity = quantity;
    }
    /**
     * Constructs a Inventory Asset, assigning a QTY to organisation
     * @param org_id Organisation ID of the organisation that owns this Asset
     * @param type The Type of Asset this is
     * @param quantity The Quantity of this asset in the system
     */
    public InventoryAsset(Integer org_id, String type, Integer quantity) {
        this.org_id = org_id;
        this.Type = type;
        this.quantity = quantity;
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
    @Override
    public Integer getQTY() {
        return this.quantity;
    }
    @Override
    public void setQTY(Integer i) {
        this.quantity = i;
    }

    public String getType() {return this.Type;}
    public void setType(String Type) {this.Type = Type;}


}

