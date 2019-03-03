package io.github.mivek.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

import io.github.mivek.model.trend.AbstractMetarTrend;

/**
 * Metar class.
 * @author mivek
 */
public class Metar extends AbstractWeatherCode {
    /**
     * Temperature.
     */
    private Integer fTemperature;
    /**
     * Dew point.
     */
    private Integer fDewPoint;
    /**
     * Altimeter in HPa..
     */
    private Integer fAltimeter;
    /**
     * Nosig value.
     */
    private boolean fNosig;
    /**
     * Auto Value.
     */
    private boolean fAuto;
    /**
     * List of runways information.
     */
    private List<RunwayInfo> fRunways;

    /**
     * List of trends.
     */
    private List<AbstractMetarTrend> fTrends;

    /**
     * Constructor.
     */
    public Metar() {
        super();
        fRunways = new ArrayList<>();
        fTrends = new ArrayList<>();
    }

    /**
     * @return the temperature
     */
    public Integer getTemperature() {
        return fTemperature;
    }

    /**
     * @param pTemperature the temperature to set
     */
    public void setTemperature(final Integer pTemperature) {
        fTemperature = pTemperature;
    }

    /**
     * @return the dewPoint
     */
    public Integer getDewPoint() {
        return fDewPoint;
    }

    /**
     * @param pDewPoint the dewPoint to set
     */
    public void setDewPoint(final Integer pDewPoint) {
        fDewPoint = pDewPoint;
    }

    /**
     * @return the altimeter in HPa.
     */
    public Integer getAltimeter() {
        return fAltimeter;
    }

    /**
     * @param pAltimeter the altimeter to set
     */
    public void setAltimeter(final Integer pAltimeter) {
        fAltimeter = pAltimeter;
    }

    /**
     * @return the runways
     */
    public List<RunwayInfo> getRunways() {
        return fRunways;
    }

    /**
     * Adds a runway to the list.
     * @param pRunwayInformation the runway to add.
     */
    public void addRunwayInfo(final RunwayInfo pRunwayInformation) {
        fRunways.add(pRunwayInformation);
    }

    /**
     * @return the nosig
     */
    public boolean isNosig() {
        return fNosig;
    }

    /**
     * @param pNosig the nosig to set
     */
    public void setNosig(final boolean pNosig) {
        fNosig = pNosig;
    }

    /**
     * @return the auto
     */
    public boolean isAuto() {
        return fAuto;
    }

    /**
     * @param pAuto the auto to set
     */
    public void setAuto(final boolean pAuto) {
        fAuto = pAuto;
    }

    /**
     * Adds a trend to the list.
     * @param pTrend the trend to add.
     */
    public void addTrend(final AbstractMetarTrend pTrend) {
        fTrends.add(Validate.notNull(pTrend));
    }

    /**
     * @return the list of trends.
     */
    public List<AbstractMetarTrend> getTrends() {
        return fTrends;
    }

}
