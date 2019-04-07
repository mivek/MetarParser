package io.github.mivek.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author mivek
 */
public abstract class AbstractWeatherContainer {
    /** The wind. */
    private Wind fWind;
    /** The visibility. */
    private Visibility fVisibility;
    /** The list of clouds. */
    private List<Cloud> fClouds;
    /** The list of weatherConditions. */
    private List<WeatherCondition> fWeatherConditions;
    /** the vertical Visibility in feet. */
    private Integer fVerticalVisibility;
    /** The wind shear. */
    private WindShear fWindShear;
    /** Indicates whether the event contains CAVOK (ceiling and visibility ok). */
    private boolean fCavok;
    /**Contains the remarks.*/
    private String fRemark;

    /**
     * Constructor to initialize the lists.
     */
    public AbstractWeatherContainer() {
        fClouds = new ArrayList<>();
        fWeatherConditions = new ArrayList<>();
    }

    /**
     * @return the wind
     */
    public final Wind getWind() {
        return fWind;
    }

    /**
     * @param pWind the wind element to set.
     */
    public final void setWind(final Wind pWind) {
        fWind = pWind;
    }

    /**
     * @return the visibility
     */
    public final Visibility getVisibility() {
        return fVisibility;
    }

    /**
     * @param pVisibility the visibility to set
     */
    public final void setVisibility(final Visibility pVisibility) {
        fVisibility = pVisibility;
    }

    /**
     * @return the clouds
     */
    public final List<Cloud> getClouds() {
        return fClouds;
    }

    /**
     * @return the weatherConditions
     */
    public final List<WeatherCondition> getWeatherConditions() {
        return fWeatherConditions;
    }

    /**
     * Adds a cloud to the list.
     * @param pCloud the cloud to add.
     * @return true if the cloud has been added in the list, false otherwise.
     */
    public boolean addCloud(final Cloud pCloud) {
        if (pCloud == null) {
            return false;
        }
        fClouds.add(pCloud);
        return true;
    }

    /**
     * Adds a weather condition to the list.
     * @param pWeatherCondition the weather condition to add.
     * @return true if the weather condition has been added to the list, false
     * otherwise.
     */
    public boolean addWeatherCondition(final WeatherCondition pWeatherCondition) {
        if (pWeatherCondition == null) {
            return false;
        }
        fWeatherConditions.add(pWeatherCondition);
        return true;
    }

    /**
     * @return the verticalVisibility in feet.
     */
    public Integer getVerticalVisibility() {
        return fVerticalVisibility;
    }

    /**
     * @param pVerticalVisibility the verticalVisibility to set
     */
    public void setVerticalVisibility(final Integer pVerticalVisibility) {
        fVerticalVisibility = pVerticalVisibility;
    }

    /**
     * @return the windShear
     */
    public WindShear getWindShear() {
        return fWindShear;
    }

    /**
     * @param pWindShear the windShear to set
     */
    public void setWindShear(final WindShear pWindShear) {
        fWindShear = pWindShear;
    }

    /**
     * @return the cavok
     */
    public boolean isCavok() {
        return fCavok;
    }

    /**
     * @param pCavok the cavok to set
     */
    public void setCavok(final boolean pCavok) {
        fCavok = pCavok;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return fRemark;
    }

    /**
     * @param pRemark the remark to set
     */
    public void setRemark(final String pRemark) {
        fRemark = pRemark;
    }

    /**
     * @return string describing the object.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).
                appendToString(fWind.toString()).
                appendToString(fVisibility.toString()).
                append("vertical visibility (ft)", fVerticalVisibility).
                append("clouds", fClouds.toString()).
                append("weather conditions", fWeatherConditions.toString()).
                appendToString(fWindShear.toString()).
                append("cavok", fCavok).
                append("remark", fRemark).
                toString();
    }
}
