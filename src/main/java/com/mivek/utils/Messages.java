package com.mivek.utils;

import java.util.ResourceBundle;

public class Messages {
	private static final String BUNDLE_NAME = "com.mivek.utils.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);
	public static final String CONVERTER_D = RESOURCE_BUNDLE.getString("Converter.D");
	public static final String CONVERTER_E = RESOURCE_BUNDLE.getString("Converter.E");
	public static final String CONVERTER_N = RESOURCE_BUNDLE.getString("Converter.N");
	public static final String CONVERTER_NE = RESOURCE_BUNDLE.getString("Converter.NE");
	public static final String CONVERTER_NSC = RESOURCE_BUNDLE.getString("Converter.NSC");
	public static final String CONVERTER_NW = RESOURCE_BUNDLE.getString("Converter.NW");
	public static final String CONVERTER_S = RESOURCE_BUNDLE.getString("Converter.S");
	public static final String CONVERTER_SE = RESOURCE_BUNDLE.getString("Converter.SE");
	public static final String CONVERTER_SW = RESOURCE_BUNDLE.getString("Converter.SW");
	public static final String CONVERTER_U = RESOURCE_BUNDLE.getString("Converter.U");
	public static final String CONVERTER_VRB = RESOURCE_BUNDLE.getString("Converter.VRB");
	public static final String CONVERTER_W = RESOURCE_BUNDLE.getString("Converter.W");

	private Messages() {
	}
}
