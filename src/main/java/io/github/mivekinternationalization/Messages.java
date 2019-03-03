package io.github.mivekinternationalization;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Messages class for internationalization.
 * @author mivek
 */
public final class Messages {
    /** The singleton instance. */
    private static final Messages INSTANCE = new Messages();
    /** Name of the bundle. */
    private static final String BUNDLE_NAME = "internationalization.messages"; //$NON-NLS-1$
    /** Bundle variable. */
    private ResourceBundle fResourceBundle;
    /**
     * Private constructor.
     */
    private Messages() {
        fResourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
    }

    /**
     * @return the Messages instance.
     */
    public static Messages getInstance() {
        return INSTANCE;
    }
    /**
     * Sets the locale of the bundle.
     * @param pLocale the locale to set.
     */
    public void setLocale(final Locale pLocale) {
        Locale.setDefault(pLocale);
        ResourceBundle.clearCache();
        fResourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, pLocale);
    }


    /**
     * @param pString the string to get
     * @return the translation of pString
     */
    public String getString(final String pString) {
        return fResourceBundle.getString(pString);
    }
}
