package pattern.matching.study.sample.asset;

public record Storage(
        String name,
        String description
) implements Asset {
    @Override
    public AssetType assetType() {
        return AssetType.STORAGE;
    }
}
