package com.mivek.model.trend;

import com.mivek.enums.WeatherChangeType;
import com.mivek.model.Metar;

/**
 * @author mivek
 *
 */
public abstract class AbstractMetarTrend extends AbstractTrend<Metar> {
	/**
	 * At attribute (can be null).
	 */
	private ATTime fAt;
	/**
	 * From attribute (can be null).
	 */
	private FMTime fFM;
	/**
	 * TILL attribute (can be null).
	 */
	private TLTime fTL;

	/**
	 * Constructor.
	 * @param pType the WeatherChangeType to set.
	 */
	protected AbstractMetarTrend(final WeatherChangeType pType) {
		super(pType);
	}

	/**
	 * @return the at
	 */
	public final ATTime getAt() {
		return fAt;
	}
	/**
	 * @param pAt the at to set
	 */
	public void setAt(final ATTime pAt) {
		fAt = pAt;
	}
	/**
	 * @return the fM
	 */
	public FMTime getFM() {
		return fFM;
	}
	/**
	 * @param pFM the fM to set
	 */
	public void setFM(final FMTime pFM) {
		fFM = pFM;
	}
	/**
	 * @return the tL
	 */
	public final TLTime getTL() {
		return fTL;
	}
	/**
	 * @param pTL the tL to set
	 */
	public final void setTL(final TLTime pTL) {
		fTL = pTL;
	}
}
