package pattern.matching.study.sample.asset;

import pattern.matching.study.sample.location.Location;

public interface Server extends Asset{
    @Override
    default AssetType assetType(){
        return AssetType.SERVER;
    }
    Location location();
    CIA cia();
}
