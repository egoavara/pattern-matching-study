package pattern.matching.study.sample.location;

public record AWSLocation(
        String region,
        String availabilityZone
) implements Location {
    @Override
    public String toLocation() {
        return STR. "\{ region }-\{ availabilityZone }-00-00-000" ;
    }
}
