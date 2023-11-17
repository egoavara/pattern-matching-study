package pattern.matching.study.sample.asset;

public record Network(
        String name,
        String description
) implements Asset {
    @Override
    public AssetType assetType() {
        return AssetType.NETWORK;
    }
}
