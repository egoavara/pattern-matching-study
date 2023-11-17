package pattern.matching.study.sample.asset;

import pattern.matching.study.sample.location.AWSLocation;

public record AWSServer(
        String name,
        String description,
        AWSLocation location,
        CIA cia
) implements Server {
    @Override
    public AssetType assetType() {
        return AssetType.SERVER;
    }
}
