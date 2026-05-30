package io.github.mivek.model;

import io.github.mivek.enums.Flag;
import io.github.mivek.enums.ReportType;
import io.github.mivek.internationalization.Messages;
import java.time.LocalTime;
import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author mivek
 * Parent class of {@link Metar} and {@link TAF}.
 */
public abstract class AbstractWeatherCode extends AbstractWeatherContainer {

    /** Day of the METAR or TAF. */
    private int day;
    /** Time of the metar. */
    private LocalTime time;
    /** Airport of the metar. */
    private Airport airport;
    /** Original message of the metar. */
    private String message;
    /** The identifier of the station. */
    private String station;
    /** Report type (METAR or SPECI). */
    private ReportType reportType;

    /** Holds the flag of the code. */
    private final EnumSet<Flag> flags;

    /**
     * Default constructor.
     */
    protected AbstractWeatherCode() {
        super();
        flags = EnumSet.noneOf(Flag.class);
    }
    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(final int day) {
        this.day = day;
    }

    /**
     * @return the time
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(final LocalTime time) {
        this.time = time;
    }

    /**
     * @return the airport
     */
    public Airport getAirport() {
        return airport;
    }

    /**
     * @param airport the airport to set
     */
    public void setAirport(final Airport airport) {
        this.airport = airport;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @return The station. the icao.
     */
    public String getStation() {
        return station;
    }

    /**
     * @param station The identifier of the station.
     */
    public void setStation(final String station) {
        this.station = station;
    }

    /**
     * @return the report type (METAR or SPECI).
     */
    public ReportType getReportType() {
        return reportType;
    }

    /**
     * @param reportType the report type to set.
     */
    public void setReportType(final ReportType reportType) {
        this.reportType = reportType;
    }

    /**
     * @return The flags of the weatherCode.
     */
    public Set<Flag> getFlags() {
        return flags;
    }

    /**
     * @return the auto
     */
    public boolean isAuto() {
        return getFlags().contains(Flag.AUTO);
    }

    /**
     * @return the amendment
     */
    public boolean isAmendment() {
        return getFlags().contains(Flag.AMD);
    }

    /**
     * @return True when the code is canceled.
     */
    public boolean isCancelled() {
        return getFlags().contains(Flag.CNL);
    }

    /**
     * @return Return true if the code is nil.
     */
    public boolean isNil() {
        return getFlags().contains(Flag.NIL);
    }

    /**
     * @return True if the code is corrected.
     */
    public boolean isCorrected() {
        return getFlags().contains(Flag.COR);
    }

    /**
     * @return a description of the object using the JVM default locale.
     */
    @Override
    public String toString() {
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
                append(Messages.getInstance().getString(locale, "ToString.day.month"), day).
                append(Messages.getInstance().getString(locale, "ToString.report.time"), time).
                append(Messages.getInstance().getString(locale, "ToString.airport"), airport).
                appendSuper(super.toString(locale)).
                append(Messages.getInstance().getString(locale, "ToString.message"), message).
                append(Messages.getInstance().getString(locale, "ToString.flags"),
                    flags.stream().map(f -> f.toString(locale)).collect(Collectors.joining(", ", "[", "]"))).
                toString();
    }
}
