package pattern.matching.study;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pattern.matching.study.sample.Bar;
import pattern.matching.study.sample.Foo;

import java.util.stream.Stream;


public class Example01 {

    private static Stream<Object> source() {
        return Stream.of(
                new Foo(),
                new Bar()
        );
    }

    @ParameterizedTest(name = "instanceof no pattern matching")
    @MethodSource("source")
    public void previous(Object obj) {
        if (obj instanceof Foo) {
            Assertions.assertEquals("Foo", ((Foo) obj).sayMyNameFoo());
        } else if (obj instanceof Bar) {
            Assertions.assertEquals("Bar", ((Bar) obj).sayMyNameBar());
        }
    }

    @ParameterizedTest(name = "instanceof pattern matching")
    @MethodSource("source")
    public void future(Object obj) {
        if (obj instanceof Foo foo) {
            Assertions.assertEquals("Foo", foo.sayMyNameFoo());
        } else if (obj instanceof Bar bar) {
            Assertions.assertEquals("Bar", bar.sayMyNameBar());
        }
    }
}
