package com.mivek.model;

/**
 * Runway class.
 * @author mivek
 */
public class RunwayInfo {
    /**
     * The name of the runway.
     */
    private String fName;
    /**
     * The minimal visibility on the runway.
     */
    private int fMinRange;
    /**
     * The maximal visibility on the runway.
     */
    private int fMaxRange;
    /**
     * The tread.
     */
    private String fTrend;

    /**
     * Getter of name.
     * @return the name.
     */
    public String getName() {
        return fName;
    }

    /**
     * Setter of name.
     * @param pName the name to set.
     */
    public void setName(final String pName) {
        fName = pName;
    }

    /**
     * Getter of minimal range.
     * @return the minRange.
     */
    public int getMinRange() {
        return fMinRange;
    }

    /**
     * The setter of minRange.
     * @param pMinRange the minRange to set.
     */
    public void setMinRange(final int pMinRange) {
        fMinRange = pMinRange;
    }

    /**
     * Getter of maxRange.
     * @return maxRange.
     */
    public int getMaxRange() {
        return fMaxRange;
    }

    /**
     * Setter of maxRange.
     * @param pMaxRange the maxrange to set.
     */
    public void setMaxRange(final int pMaxRange) {
        fMaxRange = pMaxRange;
    }

    /**
     * Getter of the trend.
     * @return the trend.
     */
    public String getTrend() {
        return fTrend;
    }

    /**
     * Setter of the trend.
     * @param pTrend Trend to set.
     */
    public void setTrend(final String pTrend) {
        fTrend = pTrend;
    }
}
