/**
 * 
 */
package com.mivek.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mivek
 *
 */
public class TAF extends WeatherCode {

	/**
	 * 
	 */
	Validity fValidity;

	List<BECMGChange> fBECMGs;
	List<FMChange> fFMs;
	List<TEMPOChange> fTempos;
	/**
	 * Constructor.
	 */
	public TAF() {
		super();
		fBECMGs = new ArrayList<>();
		fFMs = new ArrayList<>();
		fTempos = new ArrayList<>();
	}

	/**
	 * @return the validity
	 */
	public Validity getValidity() {
		return fValidity;
	}

	/**
	 * @param pValidity
	 *            the validity to set
	 */
	public void setValidity(Validity pValidity) {
		fValidity = pValidity;
	}

	/**
	 * @return the bECMGs
	 */
	public List<BECMGChange> getBECMGs() {
		return fBECMGs;
	}

	/**
	 * @return the fMs
	 */
	public List<FMChange> getFMs() {
		return fFMs;
	}

	/**
	 * @return the tempos
	 */
	public List<TEMPOChange> getTempos() {
		return fTempos;
	}

	public void addTempo(TEMPOChange pChange) {
		fTempos.add(pChange);
	}

	public void addBECMG(BECMGChange pChange) {
		fBECMGs.add(pChange);
	}

	public void addFM(FMChange pChange) {
		fFMs.add(pChange);
	}
}
