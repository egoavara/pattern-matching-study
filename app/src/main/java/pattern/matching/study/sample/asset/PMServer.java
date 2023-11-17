package pattern.matching.study.sample.asset;

import pattern.matching.study.sample.location.IDCLocation;

public record PMServer(
        String name,
        String description,
        IDCLocation location,
        CIA cia
) implements Server {
    @Override
    public AssetType assetType() {
        return AssetType.SERVER;
    }
}
