package io.github.mivek.internationalization;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Messages class for internationalization. Thread-safe via ThreadLocal.
 *
 * <p>This class provides two API styles:
 * <ul>
 *   <li><b>Stateless</b> — {@link #getString(Locale, String)} and
 *       {@link #getString(Locale, String, Object...)} require an explicit
 *       {@code Locale} parameter and never touch thread-local state.
 *       These are the preferred path for new code.</li>
 *   <li><b>Stateful (legacy)</b> — {@link #getString(String)} and
 *       {@link #getString(String, Object...)} read from a per-thread
 *       {@link ResourceBundle} controlled by {@link #setLocale(Locale)}.
 *       Scheduled for removal in a future major release.</li>
 * </ul>
 *
 * All lookups degrade gracefully: if a key is missing in the requested bundle
 * (or any ancestor), the key itself is returned instead of throwing.
 *
 * @author mivek
 */
public final class Messages {
  /** The singleton instance. */
  private static final Messages INSTANCE = new Messages();
  /** Name of the bundle. */
  private static final String BUNDLE_NAME = "internationalization.messages";
  /** Per-thread bundle holder — thread-safe, no global Locale.setDefault(). */
  private final ThreadLocal<ResourceBundle> bundleHolder =
      ThreadLocal.withInitial(() -> ResourceBundle.getBundle(BUNDLE_NAME));

  /**
   * Private constructor.
   */
  private Messages() { }

  /**
   * @return the Messages instance.
   */
  public static Messages getInstance() {
    return INSTANCE;
  }

  /**
   * Sets the locale of the bundle for the current thread.
   *
   * @param locale the locale to set.
   * @deprecated Use {@link #getString(Locale, String)} instead.
   * Scheduled for removal in a future major release.
   */
  @Deprecated
  public void setLocale(final Locale locale) {
    bundleHolder.set(ResourceBundle.getBundle(BUNDLE_NAME, locale));
  }

  /**
   * Clears the locale for the current thread, resetting it to the JVM default.
   *
   * <p>Must be called in thread-pool environments (e.g., servlets, Spring)
   * after each request to prevent locale leakage between tasks on the same thread.
   *
   * @deprecated Use {@link #getString(Locale, String)} instead.
   * Scheduled for removal in a future major release.
   */
  @Deprecated
  public void clearLocale() {
    bundleHolder.remove();
  }

  /**
   * Returns a localized string for the given key using the thread-local locale.
   *
   * @param message the key to look up.
   * @return the translated string.
   * @throws java.util.MissingResourceException if the key is not found.
   */
  public String getString(final String message) {
    return bundleHolder.get().getString(message);
  }

  /**
   * Returns a formatted localized string for the given key and arguments
   * using the thread-local locale.
   *
   * @param message   the key to look up.
   * @param arguments the arguments to fill into the message pattern.
   * @return the formatted translated string.
   * @throws java.util.MissingResourceException if the key is not found.
   */
  public String getString(final String message, final Object... arguments) {
    return MessageFormat.format(getString(message), arguments);
  }

  // ---- Stateless overloads (preferred API) ----

  /**
   * Returns a localized string for the given key and locale.
   *
   * <p>This method does <b>not</b> use the thread-local locale. It fetches
   * a {@link ResourceBundle} directly for the given locale, making it safe
   * for concurrent and multi-locale use.
   *
   * @param locale the locale to use.
   * @param key    the key to look up.
   * @return the translated string, or the key itself if not found.
   */
  public String getString(final Locale locale, final String key) {
    return getStringSafe(ResourceBundle.getBundle(BUNDLE_NAME, locale), key);
  }

  /**
   * Returns a formatted localized string for the given key, locale, and arguments.
   *
   * <p>This method does <b>not</b> use the thread-local locale. It fetches
   * a {@link ResourceBundle} directly for the given locale, making it safe
   * for concurrent and multi-locale use.
   *
   * @param locale    the locale to use.
   * @param key       the key to look up.
   * @param arguments the arguments to fill into the message pattern.
   * @return the formatted translated string, or the key if not found.
   */
  public String getString(final Locale locale, final String key, final Object... arguments) {
    return MessageFormat.format(getString(locale, key), arguments);
  }

  /**
   * Safely looks up a key from a ResourceBundle, returning the key itself
   * on {@link MissingResourceException}.
   */
  private static String getStringSafe(final ResourceBundle bundle, final String key) {
    try {
      return bundle.getString(key);
    } catch (final MissingResourceException e) {
      return key;
    }
  }
}
