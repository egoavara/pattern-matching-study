package pattern.matching.study;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pattern.matching.study.sample.asset.CIA;
import pattern.matching.study.sample.asset.DKOSServer;
import pattern.matching.study.sample.asset.PMServer;
import pattern.matching.study.sample.asset.VMServer;
import pattern.matching.study.sample.location.IDCLocation;

import java.util.Objects;
import java.util.stream.Stream;


public class Example04 {

    private static Stream<Object> source() {
        var pm00 = new PMServer("pm00", "", new IDCLocation("AY1", "032", 1, 1, 1), new CIA(1, 2, 3));
        var pm01 = new PMServer("pm01", "", new IDCLocation("AY1", "032", 1, 1, 2), new CIA(1, 2, 3));
        var vm00 = new VMServer("vm00", "", pm00, new CIA(1, 2, 3));
        var dkos00 = new DKOSServer("dkos00", "pm dkos", pm01, new CIA(1, 2, 3));
        var dkos01 = new DKOSServer("dkos01", "vm dkos", vm00, new CIA(1, 2, 3));
        return Stream.of(
                pm00,
                pm01,
                vm00,
                dkos00,
                dkos01,
                null
        );
    }

    @ParameterizedTest(name = "switch statement")
    @MethodSource("source")
    public void dkosPrevious(Object obj) {
        CIA cia;
        switch (obj) {
            case PMServer s when !Objects.isNull(s.cia()) -> cia = s.cia();
            case VMServer s when !Objects.isNull(s.cia()) -> cia = s.cia();
            case DKOSServer s when !Objects.isNull(s.cia()) -> cia = s.cia();
            case null, default -> cia = new CIA(1, 1, 1);
        }
        if (Objects.isNull(obj)) {
            Assertions.assertEquals(new CIA(1, 1, 1), cia);
        } else {
            Assertions.assertEquals(new CIA(1, 2, 3), cia);
        }
    }

    @ParameterizedTest(name = "switch expression")
    @MethodSource("source")
    public void dkosFuture(Object obj) {
        CIA cia = switch (obj) {
            case PMServer s when !Objects.isNull(s.cia()) -> s.cia();
            case VMServer s when !Objects.isNull(s.cia()) -> s.cia();
            case DKOSServer s when !Objects.isNull(s.cia()) -> s.cia();
            case null, default -> new CIA(1, 1, 1);
        };
        if (Objects.isNull(obj)) {
            Assertions.assertEquals(new CIA(1, 1, 1), cia);
        } else {
            Assertions.assertEquals(new CIA(1, 2, 3), cia);
        }
    }
}
