package org.third.common.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -4606371531618303712L;

	public BadRequestException() {
	}

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(Throwable cause) {
		super(cause);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
