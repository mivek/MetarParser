package com.mivek.enums;

/**
 * Enumeration for cloud type.
 * The first attribute is the code used in the metar.
 * The second attribute is the meaning of the code.
 * @author mivek
 *
 */
public enum CloudType {
	CB("CB", Messages.CLOUD_TYPE_CB), //$NON-NLS-1$
	TCU("TCU", Messages.CLOUD_TYPE_TCU) //$NON-NLS-1$
	;
	
	private String shortcut=""; //$NON-NLS-1$
	private String name=""; //$NON-NLS-1$
	
	CloudType(String shortcut,String name){
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
