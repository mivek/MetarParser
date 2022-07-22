package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.trend.MetarTrend;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Metar class.
 *
 * @author mivek
 */
public class Metar extends AbstractWeatherCode {
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
}
