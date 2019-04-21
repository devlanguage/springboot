package org.third.common.exception;

public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 2315282461766895679L;

	public ForbiddenException() {
	}

	public ForbiddenException(String message) {
		super(message);
	}

	public ForbiddenException(Throwable cause) {
		super(cause);
	}

	public ForbiddenException(String message, Throwable cause) {
		super(message, cause);
	}
}
