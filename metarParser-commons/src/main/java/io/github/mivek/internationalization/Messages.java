package io.github.mivek.internationalization;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Messages class for internationalization. Thread-safe via ThreadLocal.
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
  private Messages() {}

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
   */
  public void setLocale(final Locale locale) {
    bundleHolder.set(ResourceBundle.getBundle(BUNDLE_NAME, locale));
  }

  /**
   * Clears the locale for the current thread, resetting it to the JVM default.
   *
   * <p>Must be called in thread-pool environments (e.g., servlets, Spring)
   * after each request to prevent locale leakage between tasks on the same thread.
   */
  public void clearLocale() {
    bundleHolder.remove();
  }

  /**
   * @param message the string to get
   * @return the translation of message
   */
  public String getString(final String message) {
    return bundleHolder.get().getString(message);
  }

  /**
   * @param message   the translation to get
   * @param arguments the arguments to fill
   * @return the translation of the message with the arguments.
   */
  public String getString(final String message, final Object... arguments) {
    return MessageFormat.format(getString(message), arguments);
  }
}
