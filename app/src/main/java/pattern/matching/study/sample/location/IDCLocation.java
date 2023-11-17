package pattern.matching.study.sample.location;

public record IDCLocation(
        String building,
        String floor,
        int x,
        int y,
        int z
) implements Location {
    @Override
    public String toLocation() {
        return STR. "\{ building }-\{ floor }-\{ formatX(x) }-\{ formatY(y) }-\{ formatZ(z) }" ;
    }
}
