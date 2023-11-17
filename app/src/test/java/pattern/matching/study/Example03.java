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
import java.util.function.Function;
import java.util.stream.Stream;


public class Example03 {

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

    @ParameterizedTest(name = "dkos instanceof no pattern matching")
    @MethodSource("source")
    public void dkosPrevious(Object obj) {
        Function<Object, CIA> getCIA = (value) -> {
            if (Objects.isNull(value)) {
                return new CIA(1, 1, 1);
            }
            if (value instanceof PMServer) {
                var pm = (PMServer) value;
                if (Objects.isNull(pm.cia())) {
                    return new CIA(1, 1, 1);
                }
                return pm.cia();
            } else if (value instanceof VMServer) {
                var vm = (VMServer) value;
                if (Objects.isNull(vm.cia())) {
                    return new CIA(1, 1, 1);
                }
                return vm.cia();
            } else if (value instanceof DKOSServer) {
                var dkos = (DKOSServer) value;
                if (Objects.isNull(dkos.cia())) {
                    return new CIA(1, 1, 1);
                }
                return dkos.cia();
            } else {
                throw new IllegalArgumentException();
            }
        };
        if (Objects.isNull(obj)) {
            Assertions.assertEquals(new CIA(1, 1, 1), getCIA.apply(obj));
        } else {
            Assertions.assertEquals(new CIA(1, 2, 3), getCIA.apply(obj));
        }
    }

    @ParameterizedTest(name = "dkos instanceof pattern matching")
    @MethodSource("source")
    public void dkosFuture(Object obj) {
        Function<Object, CIA> getCIA = (value) -> {
            switch (value) {
                case PMServer s when !Objects.isNull(s.cia()) -> {
                    return s.cia();
                }
                case VMServer s when !Objects.isNull(s.cia()) -> {
                    return s.cia();
                }
                case DKOSServer s when !Objects.isNull(s.cia()) -> {
                    return s.cia();
                }
                case null, default -> {
                    return new CIA(1, 1, 1);
                }
            }
        };
        if (Objects.isNull(obj)) {
            Assertions.assertEquals(new CIA(1, 1, 1), getCIA.apply(obj));
        } else {
            Assertions.assertEquals(new CIA(1, 2, 3), getCIA.apply(obj));
        }
    }
}
