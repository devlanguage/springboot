package org.third.common.exception;

public class ParamsErrorException extends RuntimeException {

    private static final long serialVersionUID = -46063715328303712L;

    public ParamsErrorException() {
    }

    public ParamsErrorException(String message) {
        super(message);
    }

    public ParamsErrorException(Throwable cause) {
        super(cause);
    }

    public ParamsErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
