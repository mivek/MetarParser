package com.mivek.enums;

import java.util.ResourceBundle;

public class Messages {
	private static final String BUNDLE_NAME = "com.mivek.enums.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	public static final String CLOUD_QUANTITY_BKN = RESOURCE_BUNDLE.getString("CloudQuantity.BKN");
	public static final String CLOUD_QUANTITY_FEW = RESOURCE_BUNDLE.getString("CloudQuantity.FEW");
	public static final String CLOUD_QUANTITY_OVC = RESOURCE_BUNDLE.getString("CloudQuantity.OVC");
	public static final String CLOUD_QUANTITY_SCT = RESOURCE_BUNDLE.getString("CloudQuantity.SCT");
	public static final String CLOUD_QUANTITY_SKC = RESOURCE_BUNDLE.getString("CloudQuantity.SKC");

	public static final String CLOUD_TYPE_CB = RESOURCE_BUNDLE.getString("CloudType.CB");
	public static final String CLOUD_TYPE_TCU = RESOURCE_BUNDLE.getString("CloudType.TCU");

	public static final String DESCRIPTIVE_BC = RESOURCE_BUNDLE.getString("Descriptive.BC");
	public static final String DESCRIPTIVE_BL = RESOURCE_BUNDLE.getString("Descriptive.BL");
	public static final String DESCRIPTIVE_DR = RESOURCE_BUNDLE.getString("Descriptive.DR");
	public static final String DESCRIPTIVE_FZ = RESOURCE_BUNDLE.getString("Descriptive.FZ");
	public static final String DESCRIPTIVE_MI = RESOURCE_BUNDLE.getString("Descriptive.MI");
	public static final String DESCRIPTIVE_PR = RESOURCE_BUNDLE.getString("Descriptive.PR");
	public static final String DESCRIPTIVE_SH = RESOURCE_BUNDLE.getString("Descriptive.SH");
	public static final String DESCRIPTIVE_TS = RESOURCE_BUNDLE.getString("Descriptive.TS");

	public static final String INTENSITY_LIGHT = RESOURCE_BUNDLE.getString("Intensity.-");
	public static final String INTENSITY_HEAVY = RESOURCE_BUNDLE.getString("Intensity.+");
	public static final String INTENSITY_VC = RESOURCE_BUNDLE.getString("Intensity.VC");

	public static final String PHENOMENON_BR = RESOURCE_BUNDLE.getString("Phenomenon.BR");
	public static final String PHENOMENON_DS = RESOURCE_BUNDLE.getString("Phenomenon.DS");
	public static final String PHENOMENON_DU = RESOURCE_BUNDLE.getString("Phenomenon.DU");
	public static final String PHENOMENON_DZ = RESOURCE_BUNDLE.getString("Phenomenon.DZ");
	public static final String PHENOMENON_FC = RESOURCE_BUNDLE.getString("Phenomenon.FC");
	public static final String PHENOMENON_FG = RESOURCE_BUNDLE.getString("Phenomenon.FG");
	public static final String PHENOMENON_FU = RESOURCE_BUNDLE.getString("Phenomenon.FU");
	public static final String PHENOMENON_GR = RESOURCE_BUNDLE.getString("Phenomenon.GR");
	public static final String PHENOMENON_GS = RESOURCE_BUNDLE.getString("Phenomenon.GS");
	public static final String PHENOMENON_HZ = RESOURCE_BUNDLE.getString("Phenomenon.HZ");

	public static final String PHENOMENON_IC = RESOURCE_BUNDLE.getString("Phenomenon.IC");
	public static final String PHENOMENON_PL = RESOURCE_BUNDLE.getString("Phenomenon.PL");
	public static final String PHENOMENON_PO = RESOURCE_BUNDLE.getString("Phenomenon.PO");
	public static final String PHENOMENON_PY = RESOURCE_BUNDLE.getString("Phenomenon.PY");
	public static final String PHENOMENON_RA = RESOURCE_BUNDLE.getString("Phenomenon.RA");
	public static final String PHENOMENON_SA = RESOURCE_BUNDLE.getString("Phenomenon.SA");
	public static final String PHENOMENON_SG = RESOURCE_BUNDLE.getString("Phenomenon.SG");
	public static final String PHENOMENON_SN = RESOURCE_BUNDLE.getString("Phenomenon.SN");
	public static final String PHENOMENON_SQ = RESOURCE_BUNDLE.getString("Phenomenon.SQ");

	public static final String PHENOMENON_SS = RESOURCE_BUNDLE.getString("Phenomenon.SS");
	public static final String PHENOMENON_UP = RESOURCE_BUNDLE.getString("Phenomenon.UP");
	public static final String PHENOMENON_VA = RESOURCE_BUNDLE.getString("Phenomenon.VA");

	private Messages() {
	}

}
