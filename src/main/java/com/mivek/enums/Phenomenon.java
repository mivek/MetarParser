package com.mivek.enums;

/**
 * Enumeration for phenomenon.
 * The first attribute is the code used in the metar.
 * The second attribute is the meaning of the code.
 * @author jk
 *
 */
public enum Phenomenon {
	
	RAIN("RA", Messages.PHENOMENON_RA), //$NON-NLS-1$
	DRIZZLE("DZ", Messages.PHENOMENON_DZ), //$NON-NLS-1$
	SNOW("SN", Messages.PHENOMENON_SN), //$NON-NLS-1$
	SNOW_GRAINS("SG", Messages.PHENOMENON_SG), //$NON-NLS-1$
	ICE_PELLETS("IC", Messages.PHENOMENON_IC), //$NON-NLS-1$
	ICE_CRYSTALS("PL", Messages.PHENOMENON_PL), //$NON-NLS-1$
	HAIL("GR", Messages.PHENOMENON_GR), //$NON-NLS-1$
	SMALL_HAIL("GS", Messages.PHENOMENON_GS), //$NON-NLS-1$
	UNKNOW_PRECIPITATION("UP", Messages.PHENOMENON_UP), //$NON-NLS-1$
	FOG("FG", Messages.PHENOMENON_FG), //$NON-NLS-1$
	VOLCANIC_ASH("VA", Messages.PHENOMENON_VA), //$NON-NLS-1$
	MIST("BR", Messages.PHENOMENON_BR), //$NON-NLS-1$
	HAZE("HZ", Messages.PHENOMENON_HZ), //$NON-NLS-1$
	WIDESPREAD_DUST("DU", Messages.PHENOMENON_DU), //$NON-NLS-1$
	SMOKE("FU", Messages.PHENOMENON_FU), //$NON-NLS-1$
	SAND("SA", Messages.PHENOMENON_SA), //$NON-NLS-1$
	SPRAY("PY", Messages.PHENOMENON_PY), //$NON-NLS-1$
	SQUALL("SQ", Messages.PHENOMENON_SQ), //$NON-NLS-1$
	SAND_WHIRLS("PO", Messages.PHENOMENON_PO), //$NON-NLS-1$
	DUSTSTORM("DS", Messages.PHENOMENON_DS), //$NON-NLS-1$
	SANDSTORM("SS", Messages.PHENOMENON_SS), //$NON-NLS-1$
	FUNNEL_CLOUD("FC", Messages.PHENOMENON_FC) //$NON-NLS-1$
	
	;
	
	private String shortcut=""; //$NON-NLS-1$
	private String name=""; //$NON-NLS-1$
	
	Phenomenon(String shortcut, String name){
		this.shortcut = shortcut;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public String getShortcut() {
		return this.shortcut;
	}
}
