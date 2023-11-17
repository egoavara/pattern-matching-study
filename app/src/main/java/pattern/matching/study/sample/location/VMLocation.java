package pattern.matching.study.sample.location;

public record VMLocation(
        Location parent
) implements Location {
    @Override
    public String toLocation() {
        return parent.toLocation();
    }
}
