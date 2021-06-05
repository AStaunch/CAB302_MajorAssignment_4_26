package common;

public class InventoryAsset implements asset {
    private Integer id;
    private Integer org_id;
    private String Type;

    private Integer quantity;

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

