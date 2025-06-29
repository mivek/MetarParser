package io.github.mivek.model;

import io.github.mivek.enums.DepositCoverage;
import io.github.mivek.enums.DepositType;
import io.github.mivek.enums.RunwayInfoIndicator;
import io.github.mivek.enums.RunwayInfoTrend;
import io.github.mivek.internationalization.Messages;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Runway class.
 *
 * @author mivek
 */
public class RunwayInfo {
    /** The name of the runway. */
    private String name;
    /** The minimal visibility on the runway. */
    private Integer minRange;
    /** The maximal visibility on the runway. */
    private Integer maxRange;
    /** Indicator on the visual range: either less than or greater than. */
    private RunwayInfoIndicator indicator;
    /** The tread. */
    private RunwayInfoTrend trend;
    /** The type of deposit. */
    private DepositType depositType;
    /** The percentage of coverage. */
    private DepositCoverage coverage;
    /** The thickness of the deposit. */
    private String thickness;
    /** The breaking capacity on the runway. */
    private String brakingCapacity;

    /**
     * Getter of name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name.
     *
     * @param name the name to set.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the visual range indicator
     */
    public RunwayInfoIndicator getIndicator() {
        return indicator;
    }

    /**
     * @param indicator to set. Either "greater than" or "less than"
     */
    public void setIndicator(final RunwayInfoIndicator indicator) {
        this.indicator = indicator;
    }

    /**
     * Getter of minimal range.
     *
     * @return the minRange.
     */
    public Integer getMinRange() {
        return minRange;
    }

    /**
     * The setter of minRange.
     *
     * @param minRange the minRange to set.
     */
    public void setMinRange(final Integer minRange) {
        this.minRange = minRange;
    }

    /**
     * Getter of maxRange.
     *
     * @return maxRange.
     */
    public Integer getMaxRange() {
        return maxRange;
    }

    /**
     * Setter of maxRange.
     *
     * @param maxRange the maxrange to set.
     */
    public void setMaxRange(final Integer maxRange) {
        this.maxRange = maxRange;
    }

    /**
     * Getter of the trend.
     *
     * @return the trend.
     */
    public RunwayInfoTrend getTrend() {
        return trend;
    }

    /**
     * Setter of the trend.
     *
     * @param trend Trend to set.
     */
    public void setTrend(final RunwayInfoTrend trend) {
        this.trend = trend;
    }

    /**
     * @return The type of deposit on the runway.
     */
    public DepositType getDepositType() {
        return depositType;
    }

    /**
     * @param depositType The type of deposit to set.
     */
    public void setDepositType(final DepositType depositType) {
        this.depositType = depositType;
    }

    /**
     * @return The coverage percentage
     */
    public DepositCoverage getCoverage() {
        return coverage;
    }

    /**
     * @param coverage The percentage of coverage to set.
     */
    public void setCoverage(final DepositCoverage coverage) {
        this.coverage = coverage;
    }

    /**
     * @return The thickness of the deposit.
     */
    public String getThickness() {
        return thickness;
    }

    /**
     * @param thickness The thickness to set.
     */
    public void setThickness(final String thickness) {
        this.thickness = thickness;
    }

    /**
     * @return The braking capacity on the runway.
     */
    public String getBrakingCapacity() {
        return brakingCapacity;
    }

    /**
     * @param brakingCapacity the braking capacity to set.
     */
    public void setBrakingCapacity(final String brakingCapacity) {
        this.brakingCapacity = brakingCapacity;
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).
                append(Messages.getInstance().getString("ToString.name"), name).
                append(Messages.getInstance().getString("ToString.indicator"), indicator).
                append(Messages.getInstance().getString("ToString.visibility.min"), minRange).
                append(Messages.getInstance().getString("ToString.visibility.max"), maxRange).
                append(Messages.getInstance().getString("ToString.trend"), trend).
                append(Messages.getInstance().getString("ToString.deposit.type"), depositType).
                append(Messages.getInstance().getString("ToString.deposit.coverage"), coverage).
                append(Messages.getInstance().getString("ToString.deposit.thickness"), thickness).
                append(Messages.getInstance().getString("ToString.deposit.braking"), brakingCapacity).
                toString();
    }
}
