package com.mivek.enums;

/**
 * Enumeration for indicator.
 * The first attribute is the code used in the metar.
 * The second attribute is the meaning of the code.
 * @author mivek
 *
 */
public enum Intensity {
	LIGHT("-", Messages.INTENSITY_LIGHT), //$NON-NLS-1$
	HEAVY("+", Messages.INTENSITY_HEAVY), //$NON-NLS-1$
	IN_VICINITY("VC", Messages.INTENSITY_VC) //$NON-NLS-1$
	;
	
	private String shortcut=""; //$NON-NLS-1$
	private String name=""; //$NON-NLS-1$
	
	Intensity(String shortcut,String name){
		this.shortcut=shortcut;
		this.name =name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public String getShortcut() {
		return this.shortcut;
	}

	public static Intensity getEnum(String value) {
		for (Intensity v : values())
			if (v.getShortcut().equalsIgnoreCase(value))
				return v;
		throw new IllegalArgumentException();
	}
}
