package io.github.mivek.model;

import io.github.mivek.enums.CloudQuantity;
import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.trend.MetarTrend;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Metar class.
 *
 * @author mivek
 */
public class Metar extends AbstractWeatherCode {

    public static final Double SM_TO_KM = 1.609344;
    /** Temperature. */
    private int temperature;
    /** Dew point. */
    private int dewPoint;
    /** Altimeter in HPa. */
    private int altimeter;
    /** Nosig value. */
    private boolean nosig;
    /** List of runways information. */
    private final List<RunwayInfo> runways;
    /** List of trends. */
    private final List<MetarTrend> trends;

    /**
     * Constructor.
     */
    public Metar() {
        super();
        runways = new ArrayList<>();
        trends = new ArrayList<>();
    }

    /**
     * @return the temperature
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(final int temperature) {
        this.temperature = temperature;
    }

    /**
     * @return the dewPoint
     */
    public int getDewPoint() {
        return dewPoint;
    }

    /**
     * @param dewPoint the dewPoint to set
     */
    public void setDewPoint(final int dewPoint) {
        this.dewPoint = dewPoint;
    }

    /**
     * @return the altimeter in HPa.
     */
    public int getAltimeter() {
        return altimeter;
    }

    /**
     * @param altimeter the altimeter to set
     */
    public void setAltimeter(final int altimeter) {
        this.altimeter = altimeter;
    }

    /**
     * @return the runways
     */
    public List<RunwayInfo> getRunways() {
        return runways;
    }

    /**
     * Adds a runway to the list.
     *
     * @param runwayInformation the runway to add.
     */
    public void addRunwayInfo(final RunwayInfo runwayInformation) {
        runways.add(runwayInformation);
    }

    /**
     * @return the nosig
     */
    public boolean isNosig() {
        return nosig;
    }

    /**
     * @param nosig the nosig to set
     */
    public void setNosig(final boolean nosig) {
        this.nosig = nosig;
    }

    /**
     * Adds a trend to the list.
     *
     * @param trend the trend to add.
     */
    public void addTrend(final MetarTrend trend) {
        trends.add(Objects.requireNonNull(trend));
    }

    /**
     * @return the list of trends.
     */
    public List<MetarTrend> getTrends() {
        return trends;
    }

    /**
     * @return the US weather category.
     */
    public <T extends WeatherCategory> T getWeatherCategory(Class<T> weatherCategory) {
        return (T) computeWeatherCategory(weatherCategory.getEnumConstants());
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).
                appendSuper(super.toString()).
                append(Messages.getInstance().getString("ToString.temperature"), temperature).
                append(Messages.getInstance().getString("ToString.dew.point"), dewPoint).
                append(Messages.getInstance().getString("ToString.altimeter"), altimeter).
                append(Messages.getInstance().getString("ToString.nosig"), nosig).
                append(Messages.getInstance().getString("ToString.auto"), isAuto()).
                append(Messages.getInstance().getString("ToString.runway.info"), runways.toString()).
                append(Messages.getInstance().getString("ToString.trends"), trends.toString()).
                toString();
    }

    /**
     * Computes the weather category using ceiling and visibility.
     *
     * @param categories Array of possible weather categories
     * @return Actual weather category
     */
    private WeatherCategory computeWeatherCategory(WeatherCategory[] categories) {
        final Double visibility = parseVisibilityInKM();
        if (visibility == null) {
            return null;
        }

        final int ceiling = computeCeiling();

        return Arrays.stream(categories)
                .filter(cat -> cat.isCriteriaMet(visibility, ceiling))
                .findFirst()
                .orElse(null);
    }

    /**
     * Parses visibility value to kilometers.
     *
     * @return Visibility in kilometers
     */
    private Double parseVisibilityInKM() {
            final Matcher matcher = Pattern.compile("(\\d+)([a-z,A-Z]+)")
                    .matcher(getVisibility().getMainVisibility().replace(">", ""));
            if (!matcher.find()) {
                return null;
            }

            final int value = Integer.parseInt(matcher.group(1));
            final String unit = matcher.group(2).toUpperCase();

            switch (unit) {
                case "SM" -> { return value * SM_TO_KM; }
                case "KM" -> { return (double) value; }
                case "M" -> { return value / 1000.0; }
                default -> { return null; }
            }
    }

    /**
     * Gets lowest cloud layer that is broken or overcast.
     *
     * @return ceiling
     */
    private Integer computeCeiling() {
        return getClouds().stream()
                .sorted(Comparator.comparing(Cloud::getHeight))
                .filter(c -> CloudQuantity.BKN.equals(c.getQuantity()) || CloudQuantity.OVC.equals(c.getQuantity()))
                .findFirst()
                .map(c -> c.getHeight() - getAirport().getAltitude())
                .orElse(Integer.MAX_VALUE);
    }
}
