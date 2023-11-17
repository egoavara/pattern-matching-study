package pattern.matching.study.sample.asset;

import pattern.matching.study.sample.location.Location;

public record DKOSServer(
        String name,
        String description,
        Server server,
        CIA cia
) implements Server {
    @Override
    public AssetType assetType() {
        return AssetType.SERVER;
    }

    @Override
    public Location location() {
        return server.location();
    }
}
