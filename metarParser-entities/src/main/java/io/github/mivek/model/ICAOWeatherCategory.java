package io.github.mivek.model;

import java.util.function.BiPredicate;

/**
 * Enum of military weather categories (see ICAO Annex 2: Rules of the Air, Chapter 4: Visual Flight Rules).
 */
public enum ICAOWeatherCategory implements WeatherCategory {

    /** Weather category instrument meteorological condition. **/
    IMC((v, c) -> c < 1500 || v < 5),

    /** Weather category visual meteorological condition. **/
    VMC((v, c) -> c >= 1500 && v >= 5);

    /** function for checking criteria. **/
    private final BiPredicate<Double, Integer> criteriaFunction;

    /**
     * Creates category.
     *
     * @param criteriaFunction Function to compute category by ceiling and visibility
     */
    ICAOWeatherCategory(final BiPredicate<Double, Integer> criteriaFunction) {
        this.criteriaFunction = criteriaFunction;
    }

    /** checks if criteria for given weather category are met.
     *
     * @param visibility horizontal visibility in kilometers
     * @param ceiling cloud ceiling in feet
     * @return true if criteria are met
     */
    public boolean isCriteriaMet(final double visibility, final int ceiling) {
        return criteriaFunction.test(visibility, ceiling);
    }
}
