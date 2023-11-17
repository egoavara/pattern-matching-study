package pattern.matching.study.sample.asset;

import pattern.matching.study.sample.location.Location;

public record VMServer(
        String name,
        String description,
        Server parent,
        CIA cia
) implements Server {
    public Location location() {
        return parent.location();
    }

    @Override
    public AssetType assetType() {
        return AssetType.SERVER;
    }
}
