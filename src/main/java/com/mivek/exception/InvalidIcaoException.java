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
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public InvalidIcaoException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pMessage
	 */
	public InvalidIcaoException(String pMessage) {
		super(pMessage);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pCause
	 */
	public InvalidIcaoException(Throwable pCause) {
		super(pCause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pMessage
	 * @param pCause
	 */
	public InvalidIcaoException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pMessage
	 * @param pCause
	 * @param pEnableSuppression
	 * @param pWritableStackTrace
	 */
	public InvalidIcaoException(String pMessage, Throwable pCause, boolean pEnableSuppression,
			boolean pWritableStackTrace) {
		super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
