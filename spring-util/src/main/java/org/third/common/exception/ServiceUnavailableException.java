package org.third.common.exception;

public class ServiceUnavailableException extends RuntimeException {

	private static final long serialVersionUID = 5485678697053897451L;

	public ServiceUnavailableException() {
	}

	public ServiceUnavailableException(String message) {
		super(message);
	}

	public ServiceUnavailableException(Throwable cause) {
		super(cause);
	}

	public ServiceUnavailableException(String message, Throwable cause) {
		super(message, cause);
	}
}
