package io.github.mivek.model;

import io.github.mivek.enums.Flag;
import io.github.mivek.internationalization.Messages;
import java.util.EnumSet;
import java.util.Set;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalTime;

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
     * @return a description of the object.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append(Messages.getInstance().getString("ToString.day.month"), day).
                append(Messages.getInstance().getString("ToString.report.time"), time).
                append(Messages.getInstance().getString("ToString.airport"), airport).
                appendSuper(super.toString()).
                append(Messages.getInstance().getString("ToString.message"), message).
                append(Messages.getInstance().getString("ToString.flags"), flags).
                toString();
    }
}
