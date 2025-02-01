package io.github.mivek.model;

/**
 * Interface for different weather categories.
 *
 */
public interface WeatherCategory {

    /** checks if criteria for given weather category are met.
     *
     * @param visibility horizontal visibility in kilometers
     * @param ceiling cloud ceiling in feet
     * @return true if criteria are met
     */
    boolean isCriteriaMet(double visibility, int ceiling);
}
