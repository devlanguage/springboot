package org.third.common.exception;

/**
 * Created by liguoqi on 2018/3/22.
 */
public class ThreadUnDoneException extends RuntimeException{

  private static final long serialVersionUID = 1854144244296527419L;

  public ThreadUnDoneException() {
  }

  public ThreadUnDoneException(String message) {
    super(message);
  }

  public ThreadUnDoneException(Throwable cause) {
    super(cause);
  }

  public ThreadUnDoneException(String message, Throwable cause) {
    super(message, cause);
  }

}
