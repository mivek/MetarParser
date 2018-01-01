/**
 * 
 */
package com.mivek.enums;

import i18n.Messages;

/**
 * @author mivek
 *
 */
public enum WeatherChangeType{
	FM("FM", Messages.FROM), 
	BECMG("BECMG", Messages.BECMG), 
	TEMPO("TEMPO", Messages.TEMPO);
	
	private String fShortcut="";
	private String fName ="";
	
	private WeatherChangeType(final String pShortcut, final String pName) {
		fShortcut = pShortcut;
		fName = pName;
	}

	public String getShortcut() {
		return fShortcut;
	}

	@Override
	public String toString() {
		return fName;
	}

}
