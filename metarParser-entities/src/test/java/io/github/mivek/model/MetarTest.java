package io.github.mivek.model;

import io.github.mivek.enums.CloudQuantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class MetarTest extends AbstractWeatherContainerTest<Metar>{

    private Metar metar;

    @Override
    Metar getEntity() {
        return new Metar();
    }

    @BeforeEach
    void setUp() {
        metar = new Metar();
    }

    @DisplayName("should compute FAA weather category")
    @ParameterizedTest
    @MethodSource
    void computeFAAWeatherCategory(Integer altitude, String mainVisibility, Integer ceiling, FAAWeatherCategory expected) {
        Airport airport = new Airport();
        airport.setAltitude(altitude);
        Visibility visibility = new Visibility();
        visibility.setMainVisibility(mainVisibility);
        metar.setAirport(airport);
        metar.setVisibility(visibility);
        Cloud cloudLow = new Cloud();
        cloudLow.setHeight(200);
        cloudLow.setQuantity(CloudQuantity.FEW);
        Cloud cloudMid = new Cloud();
        cloudMid.setHeight(ceiling);
        cloudMid.setQuantity(CloudQuantity.BKN);
        Cloud cloudHigh = new Cloud();
        cloudHigh.setHeight(10000);
        cloudHigh.setQuantity(CloudQuantity.SCT);
        metar.addCloud(cloudLow);
        metar.addCloud(cloudMid);
        metar.addCloud(cloudHigh);

        FAAWeatherCategory result = metar.getWeatherCategory(WeatherCategory.FAA);

        assertThat(result, is(expected));
    }

    @DisplayName("should compute FAA weather category with no clouds at all")
    @ParameterizedTest
    @MethodSource
    void computeFAAWeatherCategoryWithNoClouds(Integer altitude, String mainVisibility, FAAWeatherCategory expected) {
        Airport airport = new Airport();
        airport.setAltitude(altitude);
        Visibility visibility = new Visibility();
        visibility.setMainVisibility(mainVisibility);
        metar.setAirport(airport);
        metar.setVisibility(visibility);

        FAAWeatherCategory result = metar.getWeatherCategory(WeatherCategory.FAA);

        assertThat(result, is(expected));
    }

    @DisplayName("should compute FAA weather category with scattered and few clouds only")
    @ParameterizedTest
    @MethodSource("computeFAAWeatherCategoryWithNoClouds")
    void computeFAAWeatherCategoryWithFewClouds(Integer altitude, String mainVisibility, FAAWeatherCategory expected) {
        Airport airport = new Airport();
        airport.setAltitude(altitude);
        Visibility visibility = new Visibility();
        visibility.setMainVisibility(mainVisibility);
        metar.setAirport(airport);
        metar.setVisibility(visibility);
        Cloud cloudLow = new Cloud();
        cloudLow.setHeight(200);
        cloudLow.setQuantity(CloudQuantity.FEW);
        Cloud cloudMid = new Cloud();
        cloudMid.setHeight(3000);
        cloudMid.setQuantity(CloudQuantity.SCT);
        Cloud cloudHigh = new Cloud();
        cloudHigh.setHeight(10000);
        cloudHigh.setQuantity(CloudQuantity.FEW);
        metar.addCloud(cloudLow);
        metar.addCloud(cloudMid);
        metar.addCloud(cloudHigh);

        FAAWeatherCategory result = metar.getWeatherCategory(WeatherCategory.FAA);

        assertThat(result, is(expected));
    }

    public static Stream<Arguments> computeFAAWeatherCategory() {
        return Stream.of(
                Arguments.of(500, "notParsable", 5000, null),
                Arguments.of(500, "500m", 5000, FAAWeatherCategory.LIFR),
                Arguments.of(500, "10km", 300, FAAWeatherCategory.LIFR),
                Arguments.of(500, "500m", 300, FAAWeatherCategory.LIFR),
                Arguments.of(500, "2km", 5000, FAAWeatherCategory.IFR),
                Arguments.of(500, "10km", 1100, FAAWeatherCategory.IFR),
                Arguments.of(500, "2km", 1100, FAAWeatherCategory.IFR),
                Arguments.of(500, "10SM", 1500, FAAWeatherCategory.MVFR),
                Arguments.of(500, "4SM", 4000, FAAWeatherCategory.MVFR),
                Arguments.of(500, "4SM", 1500, FAAWeatherCategory.MVFR),
                Arguments.of(500, "10SM", 5000, FAAWeatherCategory.VFR));
    }

    public static Stream<Arguments> computeFAAWeatherCategoryWithNoClouds() {
        return Stream.of(
                Arguments.of(500, "notParsable", null),
                Arguments.of(500, "500m", FAAWeatherCategory.LIFR),
                Arguments.of(500, "10km", FAAWeatherCategory.VFR),
                Arguments.of(500, "2km", FAAWeatherCategory.IFR),
                Arguments.of(500, "10SM", FAAWeatherCategory.VFR),
                Arguments.of(500, "4SM", FAAWeatherCategory.MVFR));
    }
}
