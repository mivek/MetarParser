package io.github.mivek.service;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherCategoryServiceTest {

    @DisplayName("should compute FAA weather category")
    @ParameterizedTest
    @MethodSource
    void computeFAAWeatherCategory(final String mainVisibility, final Integer ceiling, final FAAWeatherCategory expected) {
        final Visibility visibility = new Visibility();
        visibility.setMainVisibility(mainVisibility);
        final Cloud cloudLow = new Cloud();
        cloudLow.setHeight(200);
        cloudLow.setQuantity(CloudQuantity.FEW);
        final Cloud cloudMid = new Cloud();
        cloudMid.setHeight(ceiling);
        cloudMid.setQuantity(CloudQuantity.OVC);
        final Cloud cloudHigh = new Cloud();
        cloudHigh.setHeight(10000);
        cloudHigh.setQuantity(CloudQuantity.SCT);

        final Metar metar = new Metar();
        metar.setVisibility(visibility);
        metar.addCloud(cloudLow);
        metar.addCloud(cloudMid);
        metar.addCloud(cloudMid);

        final FAAWeatherCategory result = WeatherCategoryService.getInstance().computeWeatherCategory(metar, FAAWeatherCategory.class);

        assertEquals(expected, result);
    }

    @DisplayName("should compute FAA weather category with no clouds at all")
    @ParameterizedTest
    @MethodSource
    void computeFAAWeatherCategoryWithNoClouds(final String mainVisibility, final FAAWeatherCategory expected) {
        final Visibility visibility = new Visibility();
        visibility.setMainVisibility(mainVisibility);

        final Metar metar = new Metar();
        metar.setVisibility(visibility);

        final FAAWeatherCategory result = WeatherCategoryService.getInstance().computeWeatherCategory(metar, FAAWeatherCategory.class);

        assertEquals(expected, result);
    }

    @DisplayName("should compute FAA weather category with scattered and few clouds only")
    @ParameterizedTest
    @MethodSource("computeFAAWeatherCategoryWithNoClouds")
    void computeFAAWeatherCategoryWithFewClouds(final String mainVisibility, final FAAWeatherCategory expected) {
        final Visibility visibility = new Visibility();
        visibility.setMainVisibility(mainVisibility);
        final Cloud cloudLow = new Cloud();
        cloudLow.setHeight(200);
        cloudLow.setQuantity(CloudQuantity.FEW);
        final Cloud cloudMid = new Cloud();
        cloudMid.setHeight(3000);
        cloudMid.setQuantity(CloudQuantity.SCT);
        final Cloud cloudHigh = new Cloud();
        cloudHigh.setHeight(10000);
        cloudHigh.setQuantity(CloudQuantity.FEW);

        final Metar metar = new Metar();
        metar.setVisibility(visibility);
        metar.addCloud(cloudLow);
        metar.addCloud(cloudMid);
        metar.addCloud(cloudMid);

        final FAAWeatherCategory result = WeatherCategoryService.getInstance().computeWeatherCategory(metar, FAAWeatherCategory.class);

        assertEquals(expected, result);
    }

    @DisplayName("should compute ICAO weather category")
    @ParameterizedTest
    @MethodSource
    void computeICAOWeatherCategory(final String mainVisibility, final Integer ceiling, final ICAOWeatherCategory expected) {
        final Visibility visibility = new Visibility();
        visibility.setMainVisibility(mainVisibility);
        final Cloud cloudLow = new Cloud();
        cloudLow.setHeight(200);
        cloudLow.setQuantity(CloudQuantity.FEW);
        final Cloud cloudMid = new Cloud();
        cloudMid.setHeight(ceiling);
        cloudMid.setQuantity(CloudQuantity.BKN);
        final Cloud cloudHigh = new Cloud();
        cloudHigh.setHeight(10000);
        cloudHigh.setQuantity(CloudQuantity.SCT);

        final Metar metar = new Metar();
        metar.setVisibility(visibility);
        metar.addCloud(cloudLow);
        metar.addCloud(cloudMid);
        metar.addCloud(cloudMid);

        final ICAOWeatherCategory result = WeatherCategoryService.getInstance().computeWeatherCategory(metar, ICAOWeatherCategory.class);

        assertEquals(expected, result);
    }

    @DisplayName("should compute GAFOR weather category")
    @ParameterizedTest
    @MethodSource
    void computeGAFORWeatherCategory(final String mainVisibility, final Integer ceiling, final GAFORWeatherCategory expected) {
        final Visibility visibility = new Visibility();
        visibility.setMainVisibility(mainVisibility);
        final Cloud cloudLow = new Cloud();
        cloudLow.setHeight(200);
        cloudLow.setQuantity(CloudQuantity.FEW);
        final Cloud cloudMid = new Cloud();
        cloudMid.setHeight(ceiling);
        cloudMid.setQuantity(CloudQuantity.BKN);
        final Cloud cloudHigh = new Cloud();
        cloudHigh.setHeight(10000);
        cloudHigh.setQuantity(CloudQuantity.SCT);

        final Metar metar = new Metar();
        metar.setVisibility(visibility);
        metar.addCloud(cloudLow);
        metar.addCloud(cloudMid);
        metar.addCloud(cloudMid);

        final GAFORWeatherCategory result = WeatherCategoryService.getInstance().computeWeatherCategory(metar, GAFORWeatherCategory.class);

        assertEquals(expected, result);
    }

    @DisplayName("should compute military weather category")
    @ParameterizedTest
    @MethodSource
    void computeMilitaryWeatherCategory(final String mainVisibility, final Integer ceiling, final MilitaryWeatherCategory expected) {
        final Visibility visibility = new Visibility();
        visibility.setMainVisibility(mainVisibility);
        final Cloud cloudLow = new Cloud();
        cloudLow.setHeight(200);
        cloudLow.setQuantity(CloudQuantity.FEW);
        final Cloud cloudMid = new Cloud();
        cloudMid.setHeight(ceiling);
        cloudMid.setQuantity(CloudQuantity.BKN);
        final Cloud cloudHigh = new Cloud();
        cloudHigh.setHeight(10000);
        cloudHigh.setQuantity(CloudQuantity.SCT);

        final Metar metar = new Metar();
        metar.setVisibility(visibility);
        metar.addCloud(cloudLow);
        metar.addCloud(cloudMid);
        metar.addCloud(cloudMid);

        final MilitaryWeatherCategory result = WeatherCategoryService.getInstance().computeWeatherCategory(metar, MilitaryWeatherCategory.class);

        assertEquals(expected, result);
    }

    public static Stream<Arguments> computeFAAWeatherCategory() {
        return Stream.of(
                Arguments.of("notParsable", 5000, null),
                Arguments.of("500m", 5000, FAAWeatherCategory.LIFR),
                Arguments.of("10km", 300, FAAWeatherCategory.LIFR),
                Arguments.of("500m", 300, FAAWeatherCategory.LIFR),
                Arguments.of("2km", 5000, FAAWeatherCategory.IFR),
                Arguments.of("10km", 600, FAAWeatherCategory.IFR),
                Arguments.of("2km", 600, FAAWeatherCategory.IFR),
                Arguments.of("10SM", 1000, FAAWeatherCategory.MVFR),
                Arguments.of("4SM", 4000, FAAWeatherCategory.MVFR),
                Arguments.of("4SM", 1000, FAAWeatherCategory.MVFR),
                Arguments.of("10SM", 5000, FAAWeatherCategory.VFR));
    }

    public static Stream<Arguments> computeFAAWeatherCategoryWithNoClouds() {
        return Stream.of(
                Arguments.of("notParsable", null),
                Arguments.of("500m", FAAWeatherCategory.LIFR),
                Arguments.of("10km", FAAWeatherCategory.VFR),
                Arguments.of("2km", FAAWeatherCategory.IFR),
                Arguments.of("10SM", FAAWeatherCategory.VFR),
                Arguments.of("4SM", FAAWeatherCategory.MVFR));
    }

    public static Stream<Arguments> computeICAOWeatherCategory() {
        return Stream.of(
                Arguments.of("notParsable", 5000, null),
                Arguments.of("500m", 5000, ICAOWeatherCategory.IMC),
                Arguments.of("10km", 300, ICAOWeatherCategory.IMC),
                Arguments.of("500m", 300, ICAOWeatherCategory.IMC),
                Arguments.of("2km", 5000, ICAOWeatherCategory.IMC),
                Arguments.of("10km", 1100, ICAOWeatherCategory.IMC),
                Arguments.of("2km", 1100, ICAOWeatherCategory.IMC),
                Arguments.of("10SM", 2000, ICAOWeatherCategory.VMC),
                Arguments.of("4SM", 1000, ICAOWeatherCategory.IMC),
                Arguments.of("4SM", 4000, ICAOWeatherCategory.VMC),
                Arguments.of("10SM", 5000, ICAOWeatherCategory.VMC));
    }

    public static Stream<Arguments> computeGAFORWeatherCategory() {
        return Stream.of(
                Arguments.of("notParsable", 5000, null),
                Arguments.of("500m", 300, GAFORWeatherCategory.X),
                Arguments.of("10km", 300, GAFORWeatherCategory.M2),
                Arguments.of("6km", 300, GAFORWeatherCategory.M5),
                Arguments.of("2km", 2500, GAFORWeatherCategory.M6),
                Arguments.of("2km", 1600, GAFORWeatherCategory.M7),
                Arguments.of("2km", 800, GAFORWeatherCategory.M8),
                Arguments.of("10SM", 1500, GAFORWeatherCategory.D1),
                Arguments.of("6km", 2500, GAFORWeatherCategory.D3),
                Arguments.of("6km", 1500, GAFORWeatherCategory.D4),
                Arguments.of("10SM", 3000, GAFORWeatherCategory.O),
                Arguments.of("10SM", 6000, GAFORWeatherCategory.C));
    }

    public static Stream<Arguments> computeMilitaryWeatherCategory() {
        return Stream.of(
                Arguments.of("notParsable", 5000, null),
                Arguments.of("500m", 100, MilitaryWeatherCategory.RED),
                Arguments.of("1000m", 250, MilitaryWeatherCategory.AMB),
                Arguments.of("2000m", 500, MilitaryWeatherCategory.YLO),
                Arguments.of("4000m", 1300, MilitaryWeatherCategory.GRN),
                Arguments.of("6000m", 2300, MilitaryWeatherCategory.WHT),
                Arguments.of("10SM", 6000, MilitaryWeatherCategory.BLU));
    }
}
