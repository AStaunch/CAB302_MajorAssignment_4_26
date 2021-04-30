package asset;

public class assetUnit implements asset{
    private String assetName;
    private Integer assetID;

    public assetUnit(String assetName, Integer assetID){
        this.assetName = assetName;
        this.assetID = assetID;

    }
    @Override
    public String assetName() {
        return null;
    }

    @Override
    public Integer assetID() {
        return null;
    }
}
