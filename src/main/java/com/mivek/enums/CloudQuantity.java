package com.mivek.enums;

/**
 * Enumeration for cloud quantity.
 * The first attribute is the code used in the metar.
 * The second attribute is the meaning of the code.
 * @author mivek
 *
 */
public enum CloudQuantity {
	SKC("SKC", Messages.CLOUD_QUANTITY_SKC), //$NON-NLS-1$
	FEW("FEW", Messages.CLOUD_QUANTITY_FEW), //$NON-NLS-1$
	BKN("BKN", Messages.CLOUD_QUANTITY_BKN), //$NON-NLS-1$
	SCT("SCT", Messages.CLOUD_QUANTITY_SCT), //$NON-NLS-1$
	OVC("OVC", Messages.CLOUD_QUANTITY_OVC) //$NON-NLS-1$
	;
	
	private String shortcut=""; //$NON-NLS-1$
	private String name=""; //$NON-NLS-1$
	
	CloudQuantity(String shortcut,String name){
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


}
