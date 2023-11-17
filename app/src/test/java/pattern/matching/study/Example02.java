package pattern.matching.study;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pattern.matching.study.sample.asset.CIA;
import pattern.matching.study.sample.asset.DKOSServer;
import pattern.matching.study.sample.asset.PMServer;
import pattern.matching.study.sample.asset.VMServer;
import pattern.matching.study.sample.location.IDCLocation;

import java.util.stream.Stream;


public class Example02 {

    private static Stream<Object> source() {
        var pm00 = new PMServer("pm00", "", new IDCLocation("AY1", "032", 1, 1, 1), new CIA(1, 1, 1));
        var pm01 = new PMServer("pm01", "", new IDCLocation("AY1", "032", 1, 1, 2), new CIA(1, 1, 1));
        var vm00 = new VMServer("vm00", "", pm00, new CIA(1, 1, 1));
        var dkos00 = new DKOSServer("dkos00", "pm dkos", pm01, new CIA(1, 1, 1));
        var dkos01 = new DKOSServer("dkos01", "vm dkos", vm00, new CIA(1, 1, 1));
        return Stream.of(
                pm00,
                pm01,
                vm00,
                dkos00,
                dkos01
        );
    }

    @ParameterizedTest(name = "dkos instanceof no pattern matching")
    @MethodSource("source")
    public void dkosPrevious(Object obj) {
        if (obj instanceof DKOSServer) {
            var dkosRunner = ((DKOSServer) obj).server();
            if (dkosRunner instanceof PMServer) {
                Assertions.assertEquals("pm01", ((PMServer) dkosRunner).name());
            } else if (dkosRunner instanceof VMServer) {
                Assertions.assertEquals("vm00", ((VMServer) dkosRunner).name());
            }
        }
    }

    @ParameterizedTest(name = "dkos instanceof pattern matching")
    @MethodSource("source")
    public void dkosFuture(Object obj) {
        switch (obj) {
            case DKOSServer(
                    var name,
                    var description,
                    PMServer(var pmName, var pmDescription, var pmLocation, var pmCia),
                    var cia
            ) -> {
                Assertions.assertEquals("pm01", pmName);
            }
            case DKOSServer(
                    var name,
                    var description,
                    VMServer(var vmName, var vmDescription, var vmLocation, var vmCia),
                    var cia
            ) -> {
                Assertions.assertEquals("vm00", vmName);
            }
            default -> {
            }
        }
    }
}
