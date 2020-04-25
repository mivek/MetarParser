package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Runway class.
 * @author mivek
 */
public class RunwayInfo {
    /** The name of the runway. */
    private String name;
    /** The minimal visibility on the runway. */
    private int minRange;
    /** The maximal visibility on the runway. */
    private int maxRange;
    /** The tread. */
    private String trend;

    /**
     * Getter of name.
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name.
     * @param pName the name to set.
     */
    public void setName(final String pName) {
        name = pName;
    }

    /**
     * Getter of minimal range.
     * @return the minRange.
     */
    public int getMinRange() {
        return minRange;
    }

    /**
     * The setter of minRange.
     * @param pMinRange the minRange to set.
     */
    public void setMinRange(final int pMinRange) {
        minRange = pMinRange;
    }

    /**
     * Getter of maxRange.
     * @return maxRange.
     */
    public int getMaxRange() {
        return maxRange;
    }

    /**
     * Setter of maxRange.
     * @param pMaxRange the maxrange to set.
     */
    public void setMaxRange(final int pMaxRange) {
        maxRange = pMaxRange;
    }

    /**
     * Getter of the trend.
     * @return the trend.
     */
    public String getTrend() {
        return trend;
    }

    /**
     * Setter of the trend.
     * @param pTrend Trend to set.
     */
    public void setTrend(final String pTrend) {
        trend = pTrend;
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).
                append(Messages.getInstance().getString("ToString.name"), name).
                append(Messages.getInstance().getString("ToString.visibility.min"), minRange).
                append(Messages.getInstance().getString("ToString.visibility.max"), maxRange).
                append(Messages.getInstance().getString("ToString.trend"), trend).
                toString();

    }
}
