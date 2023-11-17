package pattern.matching.study.sample.location;

public interface Location {
    String toLocation();

    default String formatX(int x) {
        return String.format("%02d", x);
    }

    default String formatY(int x) {
        return String.format("%02d", x);
    }

    default String formatZ(int x) {
        return String.format("%03d", x);
    }
}
