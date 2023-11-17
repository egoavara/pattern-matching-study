package pattern.matching.study.sample.asset;

public record Security(
        String name,
        String description
) implements Asset {
    @Override
    public AssetType assetType() {
        return AssetType.SECURITY;
    }
}
