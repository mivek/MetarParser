package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mivek
 */
public abstract class AbstractWeatherContainer {
    /** The wind. */
    private Wind wind;
    /** The visibility. */
    private Visibility visibility;
    /** The list of clouds. */
    private final List<Cloud> clouds;
    /** The list of weatherConditions. */
    private final List<WeatherCondition> weatherConditions;
    /** the vertical Visibility in feet. */
    private Integer verticalVisibility;
    /** The wind shear. */
    private WindShear windShear;
    /** Indicates whether the event contains CAVOK (ceiling and visibility ok). */
    private boolean cavok;
    /** Contains the remarks. */
    private String remark;

    /**
     * Constructor to initialize the lists.
     */
    public AbstractWeatherContainer() {
        clouds = new ArrayList<>();
        weatherConditions = new ArrayList<>();
    }

    /**
     * @return the wind
     */
    public final Wind getWind() {
        return wind;
    }

    /**
     * @param pWind the wind element to set.
     */
    public final void setWind(final Wind pWind) {
        wind = pWind;
    }

    /**
     * @return the visibility
     */
    public final Visibility getVisibility() {
        return visibility;
    }

    /**
     * @param pVisibility the visibility to set
     */
    public final void setVisibility(final Visibility pVisibility) {
        visibility = pVisibility;
    }

    /**
     * @return the clouds
     */
    public final List<Cloud> getClouds() {
        return clouds;
    }

    /**
     * @return the weatherConditions
     */
    public final List<WeatherCondition> getWeatherConditions() {
        return weatherConditions;
    }

    /**
     * Adds a cloud to the list.
     *
     * @param pCloud the cloud to add.
     * @return true if the cloud has been added in the list, false otherwise.
     */
    public boolean addCloud(final Cloud pCloud) {
        if (pCloud == null) {
            return false;
        }
        clouds.add(pCloud);
        return true;
    }

    /**
     * Adds a weather condition to the list.
     *
     * @param pWeatherCondition the weather condition to add.
     * @return true if the weather condition has been added to the list, false
     * otherwise.
     */
    public boolean addWeatherCondition(final WeatherCondition pWeatherCondition) {
        if (pWeatherCondition == null) {
            return false;
        }
        weatherConditions.add(pWeatherCondition);
        return true;
    }

    /**
     * @return the verticalVisibility in feet.
     */
    public Integer getVerticalVisibility() {
        return verticalVisibility;
    }

    /**
     * @param pVerticalVisibility the verticalVisibility to set
     */
    public void setVerticalVisibility(final Integer pVerticalVisibility) {
        verticalVisibility = pVerticalVisibility;
    }

    /**
     * @return the windShear
     */
    public WindShear getWindShear() {
        return windShear;
    }

    /**
     * @param pWindShear the windShear to set
     */
    public void setWindShear(final WindShear pWindShear) {
        windShear = pWindShear;
    }

    /**
     * @return the cavok
     */
    public boolean isCavok() {
        return cavok;
    }

    /**
     * @param pCavok the cavok to set
     */
    public void setCavok(final boolean pCavok) {
        cavok = pCavok;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param pRemark the remark to set
     */
    public void setRemark(final String pRemark) {
        remark = pRemark;
    }

    /**
     * @return string describing the object.
     */
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        if (wind != null) {
            builder.appendToString(wind.toString());
        }
        if (visibility != null) {
            builder.appendToString(visibility.toString());
        }
        builder.append(Messages.getInstance().getString("ToString.vertical.visibility"), verticalVisibility).
                append(Messages.getInstance().getString("ToString.clouds"), clouds.toString()).
                append(Messages.getInstance().getString("ToString.weather.conditions"), weatherConditions.toString());
        if (windShear != null) {
            builder.appendToString(windShear.toString());
        }
        builder.append(Messages.getInstance().getString("ToString.cavok"), cavok).
                append(Messages.getInstance().getString("ToString.remark"), remark);
        return builder.toString();
    }
}
