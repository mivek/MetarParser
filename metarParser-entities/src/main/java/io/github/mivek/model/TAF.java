package io.github.mivek.model;

import io.github.mivek.internationalization.Messages;
import io.github.mivek.model.trend.BECMGTafTrend;
import io.github.mivek.model.trend.FMTafTrend;
import io.github.mivek.model.trend.PROBTafTrend;
import io.github.mivek.model.trend.TEMPOTafTrend;
import io.github.mivek.model.trend.validity.Validity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a TAF.
 *
 * @author mivek
 */
public class TAF extends AbstractWeatherCode {
    /** The valididty of the TAF. */
    private Validity validity;
    /** The maximum temperature. */
    private TemperatureDated maxTemperature;
    /** The minimum temperature. */
    private TemperatureDated minTemperature;
    /** List of BECMG changes. */
    private final List<BECMGTafTrend> bECMGs;
    /** List of From changes. */
    private final List<FMTafTrend> fMs;
    /** List of Tempos changes. */
    private final List<TEMPOTafTrend> tempos;
    /** List of probability changes. */
    private final List<PROBTafTrend> probs;
    /** Indicate if the taf event is ameded. */
    private boolean amendment;

    /**
     * Constructor.
     */
    public TAF() {
        super();
        bECMGs = new ArrayList<>();
        fMs = new ArrayList<>();
        tempos = new ArrayList<>();
        probs = new ArrayList<>();
    }

    /**
     * @return the validity
     */
    public Validity getValidity() {
        return validity;
    }

    /**
     * @param pValidity the validity to set
     */
    public void setValidity(final Validity pValidity) {
        validity = pValidity;
    }

    /**
     * @return the maxTemperature
     */
    public TemperatureDated getMaxTemperature() {
        return maxTemperature;
    }

    /**
     * @param pMaxTemperature the maxTemperature to set
     */
    public void setMaxTemperature(final TemperatureDated pMaxTemperature) {
        maxTemperature = pMaxTemperature;
    }

    /**
     * @return the minTemperature
     */
    public TemperatureDated getMinTemperature() {
        return minTemperature;
    }

    /**
     * @param pMinTemperature the minTemperature to set
     */
    public void setMinTemperature(final TemperatureDated pMinTemperature) {
        minTemperature = pMinTemperature;
    }

    /**
     * @return the bECMGs
     */
    public List<BECMGTafTrend> getBECMGs() {
        return bECMGs;
    }

    /**
     * @return the fMs
     */
    public List<FMTafTrend> getFMs() {
        return fMs;
    }

    /**
     * @return the probs
     */
    public List<PROBTafTrend> getProbs() {
        return probs;
    }

    /**
     * Adds a tempo change to the list.
     *
     * @param pChange the change to add.
     */
    public void addTempo(final TEMPOTafTrend pChange) {
        tempos.add(pChange);
    }

    /**
     * Adds a PROB Change to the list.
     *
     * @param pChange the change to add.
     */
    public void addProb(final PROBTafTrend pChange) {
        probs.add(pChange);
    }

    /**
     * Adds a BECMG to the list.
     *
     * @param pChange the change to add.
     */
    public void addBECMG(final BECMGTafTrend pChange) {
        bECMGs.add(pChange);
    }

    /**
     * Adds a FM change to the list.
     *
     * @param pChange the change to add.
     */
    public void addFM(final FMTafTrend pChange) {
        fMs.add(pChange);
    }

    /**
     * @return the tempos
     */
    public List<TEMPOTafTrend> getTempos() {
        return tempos;
    }

    /**
     * @return the amendment
     */
    public boolean isAmendment() {
        return amendment;
    }

    /**
     * @param pAmendment the amendment to set
     */
    public void setAmendment(final boolean pAmendment) {
        amendment = pAmendment;
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this).appendSuper(super.toString()).appendToString(validity.toString()).append(Messages.getInstance().getString("ToString.temperature.max"), maxTemperature)
                .append(Messages.getInstance().getString("ToString.temperature.min"), minTemperature).append(Messages.getInstance().getString("ToString.amendment"), amendment)
                .appendToString(bECMGs.toString()).appendToString(fMs.toString()).appendToString(tempos.toString()).appendToString(probs.toString()).toString();
    }
}
