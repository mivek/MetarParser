/**
 * 
 */
package com.mivek.exception;

/**
 * @author mivek
 *
 */
public final class InvalidIcaoException extends Exception {

	/**
	 * Version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public InvalidIcaoException() {
	}

	/**
	 * @param pMessage the message.
	 */
	public InvalidIcaoException(final String pMessage) {
		super(pMessage);
	}

	/**
	 * @param pCause the cause.
	 */
	public InvalidIcaoException(final Throwable pCause) {
		super(pCause);
	}

	/**
	 * @param pMessage the message.
	 * @param pCause the cause.
	 */
	public InvalidIcaoException(final String pMessage, final Throwable pCause) {
		super(pMessage, pCause);
	}
}
