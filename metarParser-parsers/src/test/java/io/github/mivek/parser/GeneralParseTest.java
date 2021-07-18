package io.github.mivek.parser;

import io.github.mivek.model.AbstractWeatherCode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mivek
 */
public abstract class GeneralParseTest<T extends AbstractWeatherCode> {


    public static Stream<Arguments> data() {
        return Stream.of(
                Arguments.arguments("WS020/24045KT", true),
                Arguments.arguments("05009KT", true),
                Arguments.arguments("030V113", true),
                Arguments.arguments("9999", true),
                Arguments.arguments("6 1/2SM", true),
                Arguments.arguments("1100w", true),
                Arguments.arguments("VV002", true),
                Arguments.arguments("CAVOK", true),
                Arguments.arguments("SCT026CB", true),
                Arguments.arguments("ZZZ026CB", false),
                Arguments.arguments("+SHGSRA", true),
                Arguments.arguments("+ZERT", false)
        );
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testGeneralParse(final String partToParse, final boolean expected) {
        assertEquals(expected, getSut().generalParse(getWeatherCode(), partToParse));
    }

    protected abstract T getWeatherCode();

    protected abstract AbstractParser<T> getSut();
}
