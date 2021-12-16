package io.github.mivek.parser;

import io.github.mivek.command.common.Command;
import io.github.mivek.command.common.CommonCommandSupplier;
import io.github.mivek.enums.Descriptive;
import io.github.mivek.enums.Intensity;
import io.github.mivek.enums.Phenomenon;
import io.github.mivek.exception.ParseException;
import io.github.mivek.model.AbstractWeatherContainer;
import io.github.mivek.model.Visibility;
import io.github.mivek.model.WeatherCondition;
import io.github.mivek.model.trend.TafTrend;
import io.github.mivek.model.trend.validity.Validity;
import io.github.mivek.utils.Regex;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author Jean-Kevin KPADEY
 * @param <T> The concrete subclass of AbstractWeatherContainer
 * @param <U> The input type the parser takes. Eg: String, String[]
 */
public abstract class AbstractWeatherContainerParser<T extends AbstractWeatherContainer, U> {

  /** Pattern for CAVOK. */
  protected static final String CAVOK = "CAVOK";
  /** Regex for the validity. */
  protected static final Pattern VALIDITY_REGEX = Pattern.compile("^\\d{4}/\\d{4}$");
  /** Pattern regex for the intensity of a phenomenon. */
  private static final Pattern INTENSITY_REGEX = Pattern.compile("^(-|\\+|VC)");
  /** The remark parser. */
  private final RemarkParser remarkParser;
  /** The common command commonSupplier. */
  private final CommonCommandSupplier commonSupplier;

  /**
   * DI constructor.
   * @param commonCommandSupplier The common command supplier
   * @param remarkParser The remark Parser instance.
   */
  AbstractWeatherContainerParser(
      final CommonCommandSupplier commonCommandSupplier, final RemarkParser remarkParser) {
    this.commonSupplier = commonCommandSupplier;
    this.remarkParser = remarkParser;
  }

  /**
   * This method parses the weather conditions of the metar.
   *
   * @param weatherPart String representing the weather.
   * @return a weather condition with its intensity, its descriptor and the
   * phenomenon.
   */
  WeatherCondition parseWeatherCondition(final String weatherPart) {
      WeatherCondition wc = new WeatherCondition();
      String match;
      if (Regex.find(INTENSITY_REGEX, weatherPart)) {
          match = Regex.findString(INTENSITY_REGEX, weatherPart);
          Intensity i = Intensity.getEnum(match);
          wc.setIntensity(i);
      }
      for (Descriptive des : Descriptive.values()) {
          if (Regex.findString(Pattern.compile("(" + des.getShortcut() + ")"), weatherPart) != null) {
              wc.setDescriptive(des);
              break;
          }
      }
      for (Phenomenon phe : Phenomenon.values()) {
          if (Regex.findString(Pattern.compile("(" + phe.getShortcut() + ")"), weatherPart) != null) {
              wc.addPhenomenon(phe);
          }
      }
      if (wc.isValid()) {
          return wc;
      }
      return null;
  }

  /**
   * Abstract method parse.
   *
   * @param code the message to parse.
   * @return The decoded object.
   * @throws ParseException when an error occurs during parsing.
   */
  public abstract T parse(U code) throws ParseException;

  /**
   * Method that parses common elements of an abstract weather container.
   *
   * @param container The object to update
   * @param part      the token to parse.
   * @return boolean if the token part as been parsed.
   */
  boolean generalParse(final AbstractWeatherContainer container, final String part) {
      if (CAVOK.equals(part)) {
          container.setCavok(true);
          if (container.getVisibility() == null) {
              container.setVisibility(new Visibility());
          }
          container.getVisibility().setMainVisibility(">10km");
          return true;
      }

      Command command = commonSupplier.get(part);
      if (command != null) {
          return command.execute(container, part);
      }

      WeatherCondition wc = parseWeatherCondition(part);
      return container.addWeatherCondition(wc);
  }

  /***
   * Adds the remark part to the event.
   * @param container the event to update
   * @param parts the tokens of the event
   * @param index the RMK index in the event.
   */
  void parseRMK(final AbstractWeatherContainer container, final String[] parts, final int index) {
      String[] subArray = Arrays.copyOfRange(parts, index + 1, parts.length);
      container.setRemark(remarkParser.parse(String.join(" ", subArray)));
  }

  /**
   * Parse the validity part of a {@link TAFParser} or an
   * {@link TafTrend}.
   *
   * @param validityString the string representing the validity.
   * @return a {@link Validity} object.
   */
  Validity parseValidity(final String validityString) {
    Validity validity = new Validity();
    String[] validityPart = validityString.split("/");
    validity.setStartDay(Integer.parseInt(validityPart[0].substring(0, 2)));
    validity.setStartHour(Integer.parseInt(validityPart[0].substring(2)));
    validity.setEndDay(Integer.parseInt(validityPart[1].substring(0, 2)));
    validity.setEndHour(Integer.parseInt(validityPart[1].substring(2)));
    return validity;
  }
}
