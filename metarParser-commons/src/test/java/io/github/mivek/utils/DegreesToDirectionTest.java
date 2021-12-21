package io.github.mivek.utils;

import io.github.mivek.internationalization.Messages;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class DegreesToDirectionTest {

    static Stream<Arguments> data() {
        return Stream.of(
                arguments("Converter.E", "80"),
                arguments("Converter.NNE", "30"),
                arguments("Converter.SSW", "200"),
                arguments("Converter.W", "280"),
                arguments("Converter.WNW", "300"),
                arguments("Converter.SE", "130"),
                arguments("Converter.SW", "230"),
                arguments("Converter.N", "2"),
                arguments("Converter.NNW", "345"),
                arguments("Converter.N", "350"),
                arguments("Converter.VRB","anyString")
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    void testDegreesToDirection(String direction, String degrees) {
        assertEquals(Messages.getInstance().getString(direction), Converter.degreesToDirection(degrees));
    }
}
