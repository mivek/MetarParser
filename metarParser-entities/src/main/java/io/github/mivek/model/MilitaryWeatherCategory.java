package io.github.mivek.model;

import java.util.function.BiPredicate;

/**
 * Enum of military weather categories (see https://semarv.weebly.com/uploads/3/1/8/7/3187688/military_metar_codes_v1.5.pdf).
 */
public enum MilitaryWeatherCategory implements WeatherCategory {

    /** Weather category red. **/
    RED((v, c) -> c < 200 && v < 0.8),

    /** Weather category amber. **/
    AMB((v, c) -> c >= 200 && c < 300 && v >= 0.8 && v < 1.6),

    /** Weather category yellow. **/
    YLO((v, c) -> c >= 300 && c < 700 && v >= 1.6 && v < 3.7),

    /** Weather category green. **/
    GRN((v, c) -> c >= 700 && c < 1500 && v >= 3.7 && v < 5.0),

    /** Weather category white. **/
    WHT((v, c) -> c >= 1500 && c < 2500 && v >= 5.0 && v < 8.0),

    /** Weather category blue. **/
    BLU((v, c) -> c >= 2500 && v >= 8.0);

    /** function for checking criteria. **/
    private final BiPredicate<Double, Integer> criteriaFunction;

    /**
     * Creates category.
     *
     * @param criteriaFunction Function to compute category by ceiling and visibility
     */
    MilitaryWeatherCategory(final BiPredicate<Double, Integer> criteriaFunction) {
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
