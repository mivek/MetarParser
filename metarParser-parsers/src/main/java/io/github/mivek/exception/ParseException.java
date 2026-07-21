package io.github.mivek.exception;

import java.io.Serial;

/**
 * @author mivek
 */
public class ParseException extends Exception {
    /** serialVersionUID. */
    @Serial
    private static final long serialVersionUID = -9022781702124062628L;
    /** The error code. */
    private final ErrorCodes codes;
    /** The error type, may be null. */
    private final ParseErrorType type;
    /** The offending token, may be null. */
    private final String offendingToken;
    /** The token index in the tokenized input. */
    private final int position;

    /**
     * Constructor with error code.
     *
     * @param code the error code.
     */
    public ParseException(final ErrorCodes code) {
        this(code, null, null, -1);
    }

    /**
     * Constructor with error code and structured metadata.
     *
     * @param code the error code.
     * @param type the parse error type.
     * @param offendingToken the token that caused the failure.
     * @param position the token index in the tokenized input.
     */
    public ParseException(final ErrorCodes code, final ParseErrorType type, final String offendingToken, final int position) {
        super(offendingToken == null ? code.toString() : code.toString() + " [" + offendingToken + "]");
        this.codes = code;
        this.type = type;
        this.offendingToken = offendingToken;
        this.position = position;
    }

    /**
     * @return the error code.
     */
    public ErrorCodes getErrorCode() {
        return codes;
    }

    /**
     * @return the error type, or null if not set.
     */
    public ParseErrorType getType() {
        return type;
    }

    /**
     * @return the offending token, or null if not set.
     */
    public String getOffendingToken() {
        return offendingToken;
    }

    /**
     * @return the token index in the tokenized input.
     */
    public int getPosition() {
        return position;
    }
}
