package io.github.mivek.model;

import io.github.mivek.enums.DepositCoverage;
import io.github.mivek.enums.DepositType;
import io.github.mivek.enums.RunwayInfoIndicator;
import io.github.mivek.enums.RunwayInfoTrend;
import io.github.mivek.enums.LengthUnit;
import io.github.mivek.internationalization.Messages;
import java.util.Locale;
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
    /** The runway visual range unit. */
    private LengthUnit unit;
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
     * Getter of runway visual range unit.
     *
     * @return the runway visual range unit.
     */
    public LengthUnit getUnit() {
        return unit;
    }

    /**
     * Setter of runway visual range unit.
     *
     * @param unit the runway visual range unit to set.
     */
    public void setUnit(final LengthUnit unit) {
        this.unit = unit;
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
        return toString(Locale.getDefault());
    }

    /**
     * Returns a locale-aware string representation.
     * @param locale the locale to use for labels and sub-objects.
     * @return the string representation.
     */
    public String toString(final Locale locale) {
        return new ToStringBuilder(this).
                append(Messages.getInstance().getString(locale, "ToString.name"), name).
                append(Messages.getInstance().getString(locale, "ToString.indicator"), localized(indicator, locale)).
                append(Messages.getInstance().getString(locale, "ToString.visibility.min"), minRange).
                append(Messages.getInstance().getString(locale, "ToString.visibility.max"), maxRange).
                append(Messages.getInstance().getString(locale, "ToString.runway.unit"), unit).
                append(Messages.getInstance().getString(locale, "ToString.trend"), localized(trend, locale)).
                append(Messages.getInstance().getString(locale, "ToString.deposit.type"), localized(depositType, locale)).
                append(Messages.getInstance().getString(locale, "ToString.deposit.coverage"), localized(coverage, locale)).
                append(Messages.getInstance().getString(locale, "ToString.deposit.thickness"), thickness).
                append(Messages.getInstance().getString(locale, "ToString.deposit.braking"), brakingCapacity).
                toString();
    }

    private static String localized(final Object obj, final Locale locale) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof RunwayInfoIndicator i) {
            return i.toString(locale);
        }
        if (obj instanceof RunwayInfoTrend t) {
            return t.toString(locale);
        }
        if (obj instanceof DepositType d) {
            return d.toString(locale);
        }
        if (obj instanceof DepositCoverage c) {
            return c.toString(locale);
        }
        return obj.toString();
    }
}
