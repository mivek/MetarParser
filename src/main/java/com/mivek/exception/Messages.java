package com.mivek.exception;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
	private static final String BUNDLE_NAME = "com.mivek.exception.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault());

	public static final String INVALID_ICAO = RESOURCE_BUNDLE.getString("MetarFacade.InvalidIcao");

	private Messages() {
	}
}
