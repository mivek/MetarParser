package io.github.mivek.model;

import java.util.function.BiPredicate;

/**
 * Enum of GAFOR weather categories (see https://www.dwd.de/SharedDocs/broschueren/DE/luftfahrt/gafor.pdf?__blob=publicationFile&amp;v=7).
 */
public enum GAFORWeatherCategory implements WeatherCategory {

    /** Weather category X. **/
    X((v, c) -> v < 1.5 && c < 500),

    /** Weather category M2X. **/
    M2((v, c) -> v >= 8 && c < 1000),

    /** Weather category M5. **/
    M5((v, c) -> v >= 5 && v < 8 && c < 1000),

    /** Weather category M6. **/
    M6((v, c) -> v >= 1.5 && v < 5 && c >= 2000),

    /** Weather category M7. **/
    M7((v, c) -> v >= 1.5 && v < 5 && c >= 1000 && c < 2000),

    /** Weather category M8. **/
    M8((v, c) -> v >= 1.5 && v < 5 && c < 1000),

    /** Weather category D1. **/
    D1((v, c) -> v >= 8 && c >= 1000 && c < 2000),

    /** Weather category D3. **/
    D3((v, c) -> v >= 5 && v < 8 && c >= 2000),

    /** Weather category D4. **/
    D4((v, c) -> v >= 5 && v < 8 && c >= 1000 && c < 2000),

    /** Weather category O. **/
    O((v, c) -> v >= 8 && c >= 2000 && c < 5000),

    /** Weather category C. **/
    C((v, c) -> v >= 10 && c >= 5000);

    /** function for checking criteria. **/
    private final BiPredicate<Double, Integer> criteriaFunction;

    /**
     * Creates category.
     *
     * @param criteriaFunction Function to compute category by ceiling and visibility
     */
    GAFORWeatherCategory(final BiPredicate<Double, Integer> criteriaFunction) {
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
