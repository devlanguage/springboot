package org.third.common.exception;

public class RequestTimeoutException extends RuntimeException {

	private static final long serialVersionUID = 5485678697053897451L;

	public RequestTimeoutException() {
	}

	public RequestTimeoutException(String message) {
		super(message);
	}

	public RequestTimeoutException(Throwable cause) {
		super(cause);
	}

	public RequestTimeoutException(String message, Throwable cause) {
		super(message, cause);
	}
}
