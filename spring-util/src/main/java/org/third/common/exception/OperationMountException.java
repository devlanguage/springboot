package org.third.common.exception;

public class OperationMountException extends RuntimeException {

    private static final long serialVersionUID = -460637153282112L;

    public OperationMountException() {
    }

    public OperationMountException(String message) {
        super(message);
    }

    public OperationMountException(Throwable cause) {
        super(cause);
    }

    public OperationMountException(String message, Throwable cause) {
        super(message, cause);
    }
}
