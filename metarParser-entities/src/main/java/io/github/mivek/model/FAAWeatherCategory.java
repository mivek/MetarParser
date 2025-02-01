package io.github.mivek.model;

import io.github.mivek.utils.Converter;

import java.util.function.BiPredicate;

/**
 * Enum of military weather categories (see https://www.faasafety.gov/gslac/alc/libview_printerfriendly.aspx?id=9091).
 */
public enum FAAWeatherCategory implements WeatherCategory {

    /** Weather category low IFR. **/
    LIFR((v, c) -> c < 500 || v < 1),

    /** Weather category IFR. **/
    IFR((v, c) -> c >= 500 && c < 1000 || v >= 1 && v < 3),

    /** Weather category marginal VFR. **/
    MVFR((v, c) -> c >= 1000 && c <= 3000 || v >= 3 && v <= 5),

    /** Weather category VFR. **/
    VFR((v, c) -> c > 3000 && v > 5);

    /** function for checking criteria. **/
    private final BiPredicate<Double, Integer> criteriaFunction;

    /**
     * Creates category.
     *
     * @param criteriaFunction Function to compute category by ceiling and visibility
     */
    FAAWeatherCategory(final BiPredicate<Double, Integer> criteriaFunction) {
        this.criteriaFunction = criteriaFunction;
    }

    /** checks if criteria for given weather category are met.
     *
     * @param visibility horizontal visibility in kilometers
     * @param ceiling cloud ceiling in feet
     * @return true if criteria are met
     */
    public boolean isCriteriaMet(final double visibility, final int ceiling) {
        return criteriaFunction.test(Converter.kmToSM(visibility), ceiling);
    }
}
