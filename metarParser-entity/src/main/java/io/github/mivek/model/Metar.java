package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.trend.AbstractMetarTrend;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Metar class.
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
    /** Auto Value. */
    private boolean auto;
    /** List of runways information. */
    private List<RunwayInfo> runways;
    /** List of trends. */
    private List<AbstractMetarTrend> trends;

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
     * @param pTemperature the temperature to set
     */
    public void setTemperature(final Integer pTemperature) {
        temperature = pTemperature;
    }

    /**
     * @return the dewPoint
     */
    public Integer getDewPoint() {
        return dewPoint;
    }

    /**
     * @param pDewPoint the dewPoint to set
     */
    public void setDewPoint(final Integer pDewPoint) {
        dewPoint = pDewPoint;
    }

    /**
     * @return the altimeter in HPa.
     */
    public Integer getAltimeter() {
        return altimeter;
    }

    /**
     * @param pAltimeter the altimeter to set
     */
    public void setAltimeter(final Integer pAltimeter) {
        altimeter = pAltimeter;
    }

    /**
     * @return the runways
     */
    public List<RunwayInfo> getRunways() {
        return runways;
    }

    /**
     * Adds a runway to the list.
     * @param pRunwayInformation the runway to add.
     */
    public void addRunwayInfo(final RunwayInfo pRunwayInformation) {
        runways.add(pRunwayInformation);
    }

    /**
     * @return the nosig
     */
    public boolean isNosig() {
        return nosig;
    }

    /**
     * @param pNosig the nosig to set
     */
    public void setNosig(final boolean pNosig) {
        nosig = pNosig;
    }

    /**
     * @return the auto
     */
    public boolean isAuto() {
        return auto;
    }

    /**
     * @param pAuto the auto to set
     */
    public void setAuto(final boolean pAuto) {
        auto = pAuto;
    }

    /**
     * Adds a trend to the list.
     * @param pTrend the trend to add.
     */
    public void addTrend(final AbstractMetarTrend pTrend) {
        trends.add(Validate.notNull(pTrend));
    }

    /**
     * @return the list of trends.
     */
    public List<AbstractMetarTrend> getTrends() {
        return trends;
    }

    @Override public final String toString() {
        return new ToStringBuilder(this).
                appendSuper(super.toString()).
                append(Messages.getInstance().getString("ToString.temperature"), temperature).
                append(Messages.getInstance().getString("ToString.dew.point"), dewPoint).
                append(Messages.getInstance().getString("ToString.altimeter"), altimeter).
                append(Messages.getInstance().getString("ToString.nosig"), nosig).
                append(Messages.getInstance().getString("ToString.auto"), auto).
                append(Messages.getInstance().getString("ToString.runway.info"), runways.toString()).
                append(Messages.getInstance().getString("ToString.trends"), trends.toString()).
                toString();
    }
}
