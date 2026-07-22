package io.github.mivek.exception;

/**
 * @author mivek
 */
public enum ParseErrorType {
    /** Station parse error. */
    STATION,
    /** Delivery time parse error. */
    DELIVERY_TIME,
    /** Validity parse error. */
    VALIDITY,
    /** Temperature parse error. */
    TEMPERATURE,
    /** Trend header parse error. */
    TREND,
    /** Generic / unknown parse error. */
    INVALID_MESSAGE
}
