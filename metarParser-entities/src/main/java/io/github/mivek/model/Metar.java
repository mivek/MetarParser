package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.trend.MetarTrend;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Metar class.
 *
 * @author mivek
 */
public class Metar extends AbstractWeatherCode {
    /** Temperature. */
    private Integer temperature;
    /** Dew point. */
    private Integer dewPoint;
    /** Altimeter in HPa. */
    private Integer altimeter;
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
    public Integer getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(final Integer temperature) {
        this.temperature = temperature;
    }

    /**
     * @return the dewPoint
     */
    public Integer getDewPoint() {
        return dewPoint;
    }

    /**
     * @param dewPoint the dewPoint to set
     */
    public void setDewPoint(final Integer dewPoint) {
        this.dewPoint = dewPoint;
    }

    /**
     * @return the altimeter in HPa.
     */
    public Integer getAltimeter() {
        return altimeter;
    }

    /**
     * @param altimeter the altimeter to set
     */
    public void setAltimeter(final Integer altimeter) {
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

    @Override
    public final String toString() {
        return toString(Locale.getDefault());
    }

    /**
     * Returns a locale-aware string representation.
     * @param locale the locale to use for labels and sub-objects.
     * @return the string representation.
     */
    @Override
    public String toString(final Locale locale) {
        return new ToStringBuilder(this).
                appendSuper(super.toString(locale)).
                append(Messages.getInstance().getString(locale, "ToString.temperature"), temperature).
                append(Messages.getInstance().getString(locale, "ToString.dew.point"), dewPoint).
                append(Messages.getInstance().getString(locale, "ToString.altimeter"), altimeter).
                append(Messages.getInstance().getString(locale, "ToString.nosig"), nosig).
                append(Messages.getInstance().getString(locale, "ToString.auto"), isAuto()).
                append(Messages.getInstance().getString(locale, "ToString.runway.info"),
                    runways.stream().map(r -> r.toString(locale)).collect(Collectors.joining(", ", "[", "]"))).
                append(Messages.getInstance().getString(locale, "ToString.trends"),
                    trends.stream().map(t -> t.toString(locale)).collect(Collectors.joining(", ", "[", "]"))).
                toString();
    }
}
