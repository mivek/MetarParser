package com.mivek.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Message class for internationalization.
 *
 * @author mivek
 *
 */
public final class Messages {
	/**
	 * Bundle name.
	 */
	private static final String BUNDLE_NAME = "com.mivek.utils.messages"; //$NON-NLS-1$

	/**
	 * Bundle instance.
	 */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault());
	/**
	 * Decreasing.
	 */
	public static final String CONVERTER_D = RESOURCE_BUNDLE.getString("Converter.D");
	/**
	 * East.
	 */
	public static final String CONVERTER_E = RESOURCE_BUNDLE.getString("Converter.E");
	/**
	 * North.
	 */
	public static final String CONVERTER_N = RESOURCE_BUNDLE.getString("Converter.N");
	/**
	 * North east.
	 */
	public static final String CONVERTER_NE = RESOURCE_BUNDLE.getString("Converter.NE");
	/**
	 * No significant change.
	 */
	public static final String CONVERTER_NSC = RESOURCE_BUNDLE.getString("Converter.NSC");
	/**
	 * North West.
	 */
	public static final String CONVERTER_NW = RESOURCE_BUNDLE.getString("Converter.NW");
	/**
	 * South.
	 */
	public static final String CONVERTER_S = RESOURCE_BUNDLE.getString("Converter.S");
	/**
	 * South East.
	 */
	public static final String CONVERTER_SE = RESOURCE_BUNDLE.getString("Converter.SE");
	/**
	 * South West.
	 */
	public static final String CONVERTER_SW = RESOURCE_BUNDLE.getString("Converter.SW");
	/**
	 * Uprising.
	 */
	public static final String CONVERTER_U = RESOURCE_BUNDLE.getString("Converter.U");
	/**
	 * Variable.
	 */
	public static final String CONVERTER_VRB = RESOURCE_BUNDLE.getString("Converter.VRB");
	/**
	 * West.
	 */
	public static final String CONVERTER_W = RESOURCE_BUNDLE.getString("Converter.W");

	/**
	 * Constructor.
	 */
	private Messages() {
	}
}
