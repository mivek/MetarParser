package io.github.mivek.service;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.Cloud;
import io.github.mivek.model.WeatherCategory;
import io.github.mivek.utils.Converter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Class providing a service to calculate weather categories from clouds and visibility.
 *
 */
public final class WeatherCategoryService {

    /** Instance. **/
    private static final WeatherCategoryService INSTANCE = new WeatherCategoryService();

    /**
     * Private constructor.
     */
    private WeatherCategoryService() { }

    /**
     * @param weatherContainer {@link io.github.mivek.model.Metar} or {@link io.github.mivek.model.TAF} object
     * @param weatherCategory type of weather category, see {@link WeatherCategory}
     * @param <T> One of {@link io.github.mivek.model.FAAWeatherCategory}, {@link io.github.mivek.model.GAFORWeatherCategory},
     *           {@link io.github.mivek.model.ICAOWeatherCategory}, {@link io.github.mivek.model.MilitaryWeatherCategory}
     * @return the weather category.
     */
    public <T extends WeatherCategory> T computeWeatherCategory(final AbstractWeatherContainer weatherContainer, final Class<T> weatherCategory) {
        return (T) computeWeatherCategory(weatherContainer, weatherCategory.getEnumConstants());
    }

    /**
     * Returns a instance of the class.
     *
     * @return the instance of the class.
     */
    public static WeatherCategoryService getInstance() {
        return INSTANCE;
    }

    /**
     * computes weather category for given clouds and visibility.
     *
     * @param weatherContainer Meter or TAF
     * @param categories Array of possible weather categories
     * @return Weather category
     */
    private WeatherCategory computeWeatherCategory(final AbstractWeatherContainer weatherContainer, final WeatherCategory[] categories) {
        final Double visibilityDistance = Converter.convertVisibilityToKM(weatherContainer.getVisibility().getMainVisibility());
        if (visibilityDistance == null) {
            return null;
        }

        final int ceiling = computeCeiling(weatherContainer.getClouds());

        return Arrays.stream(categories)
                .filter(cat -> cat.isCriteriaMet(visibilityDistance, ceiling))
                .findFirst()
                .orElse(null);
    }

    /**
     * computes ceiling from given list of clouds.
     * @param clouds List of clouds from Metar or TAF
     * @return lowest height of broken or overcast cloud layer
     */
    private Integer computeCeiling(final List<Cloud> clouds) {
        return clouds.stream()
                .filter(c -> c.getHeight() != null)
                .sorted(Comparator.comparing(Cloud::getHeight))
                .filter(c -> CloudQuantity.BKN.equals(c.getQuantity()) || CloudQuantity.OVC.equals(c.getQuantity()))
                .findFirst()
                .map(Cloud::getHeight)
                .orElse(Integer.MAX_VALUE);
    }
}
