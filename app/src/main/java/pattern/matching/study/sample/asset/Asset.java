package pattern.matching.study.sample.asset;

public interface Asset {
    enum AssetType {
        SERVER,
        NETWORK,
        STORAGE,
        SECURITY,
    }

    AssetType assetType();
}
