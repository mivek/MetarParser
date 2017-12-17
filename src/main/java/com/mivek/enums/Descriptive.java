package com.mivek.enums;

/**
 * Enumeration for descriptive.
 * The first attribute is the code used in the metar.
 * The second attribute is the meaning of the code.
 * @author mivek
 *
 */
public enum Descriptive {
	SHOWERS("SH", Messages.DESCRIPTIVE_SH), //$NON-NLS-1$
	SHALLOW("MI", Messages.DESCRIPTIVE_MI), //$NON-NLS-1$
	PATCHES("BC", Messages.DESCRIPTIVE_BC), //$NON-NLS-1$
	PARTIAL("PR", Messages.DESCRIPTIVE_PR), //$NON-NLS-1$
	DRIFTING("DR", Messages.DESCRIPTIVE_DR), //$NON-NLS-1$
	THUNDERSTORM("TS", Messages.DESCRIPTIVE_TS), //$NON-NLS-1$
	BLOWING("BL", Messages.DESCRIPTIVE_BL), //$NON-NLS-1$
	FREEZING("FZ", Messages.DESCRIPTIVE_FZ) //$NON-NLS-1$
	;
	
	private String shortcut=""; //$NON-NLS-1$
	private String name=""; //$NON-NLS-1$
	
	Descriptive(String shortcut, String name){
		this.shortcut = shortcut;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public String getShortcut()
	{
		return this.shortcut;
	}
}
