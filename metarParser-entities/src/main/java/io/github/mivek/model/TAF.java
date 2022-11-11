package io.github.mivek.model;

import io.github.mivek.enums.WeatherChangeType;
import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.trend.AbstractTafTrend;
import io.github.mivek.model.trend.FMTafTrend;
import io.github.mivek.model.trend.TafProbTrend;
import io.github.mivek.model.trend.TafTrend;
import io.github.mivek.model.trend.validity.AbstractValidity;
import io.github.mivek.model.trend.validity.Validity;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class representing a TAF.
 *
 * @author mivek
 */
public final class TAF extends AbstractWeatherCode implements ITafGroups {
    /** The valididty of the TAF. */
    private Validity validity;
    /** The maximum temperature. */
    private TemperatureDated maxTemperature;
    /** The minimum temperature. */
    private TemperatureDated minTemperature;
    /** List of From changes. */
    private final List<AbstractTafTrend<? extends AbstractValidity>> trends;
    /** Taf icing. */
    private final List<Icing> icings;
    /** Taf turbulence. */
    private final List<Turbulence> turbulences;

    /**
     * Constructor.
     */
    public TAF() {
        super();
        trends = new ArrayList<>();
        icings = new ArrayList<>();
        turbulences = new ArrayList<>();
    }

    /**
     * @return the validity
     */
    public Validity getValidity() {
        return validity;
    }

    /**
     * @param validity the validity to set
     */
    public void setValidity(final Validity validity) {
        this.validity = validity;
    }

    /**
     * @return the maxTemperature
     */
    public TemperatureDated getMaxTemperature() {
        return maxTemperature;
    }

    /**
     * @param maxTemperature the maxTemperature to set
     */
    public void setMaxTemperature(final TemperatureDated maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    /**
     * @return the minTemperature
     */
    public TemperatureDated getMinTemperature() {
        return minTemperature;
    }

    /**
     * @param minTemperature the minTemperature to set
     */
    public void setMinTemperature(final TemperatureDated minTemperature) {
        this.minTemperature = minTemperature;
    }

    /**
     * @return the bECMGs
     */
    public List<TafTrend> getBECMGs() {
        return trends.stream().filter(trend -> trend.getType().equals(WeatherChangeType.BECMG)).map(TafTrend.class::cast).toList();
    }

    /**
     * @return the fMs
     */
    public List<FMTafTrend> getFMs() {
        return trends.stream().filter(trend -> trend.getType().equals(WeatherChangeType.FM)).map(FMTafTrend.class::cast).toList();
    }

    /**
     * @return the probs
     */
    public List<TafProbTrend> getProbs() {
        return trends.stream().filter(trend -> trend.getType().equals(WeatherChangeType.PROB)).map(TafProbTrend.class::cast).toList();
    }

    /**
     * @return the tempos
     */
    public List<TafProbTrend> getTempos() {
        return trends.stream().filter(a -> a.getType().equals(WeatherChangeType.TEMPO)).map(TafProbTrend.class::cast).toList();
    }

    /**
     * @return inter changes.
     */
    public List<TafTrend> getInters() {
        return trends.stream().filter(trend -> trend.getType().equals(WeatherChangeType.INTER)).map(TafTrend.class::cast).toList();
    }

    /**
     * Adds a trend to the trend list.
     * @param trend The trend to add.
     */
    public void addTrend(final AbstractTafTrend<? extends AbstractValidity> trend) {
        this.trends.add(trend);
    }

    @Override
    public List<Icing> getIcings() {
        return icings;
    }

    @Override
    public List<Turbulence> getTurbulences() {
        return turbulences;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString()).appendToString(validity.toString()).append(Messages.getInstance().getString("ToString.temperature.max"), maxTemperature)
                .append(Messages.getInstance().getString("ToString.temperature.min"), minTemperature).append(Messages.getInstance().getString("ToString.amendment"), isAmendment())
            .appendToString(trends.toString()).append(turbulences.toString()).append(icings.toString()).toString();

    }
}
