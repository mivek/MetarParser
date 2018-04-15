package com.mivek.enums;

import i18n.Messages;

/**
 * @author mivek
 *
 */
public enum WeatherChangeType {
	/**
	 * From enumeration.
	 */
	FM("FM", Messages.FROM),
	/**
	 * Becoming enumeration.
	 */
	BECMG("BECMG", Messages.BECMG),
	/**
	 * Tempo enumeration.
	 */
	TEMPO("TEMPO", Messages.TEMPO),
	/**
	 * Probability change.
	 */
	PROB("PROB", Messages.PROB);
	/**
	 * Shortcut attribute.
	 */
	private String fShortcut = "";
	/**
	 * Name of the enumeration.
	 */
	private String fName = "";

	/**
	 * Constructor.
	 * @param pShortcut
	 *            the shortcut of the enumeration
	 * @param pName
	 *            the name of the enumeration
	 */
	private WeatherChangeType(final String pShortcut, final String pName) {
		fShortcut = pShortcut;
		fName = pName;
	}

	/**
	 * @return the shortcut.
	 */
	public String getShortcut() {
		return fShortcut;
	}

	@Override
	public String toString() {
		return fName;
	}

}
