package org.third.common.exception;

/**
 * Created by liguoqi on 2017/12/16.
 */
public class ConvertJsonException extends RuntimeException{

  public ConvertJsonException() {
  }

  public ConvertJsonException(String message) {
    super(message);
  }

  public ConvertJsonException(Throwable cause) {
    super(cause);
  }

  public ConvertJsonException(String message, Throwable cause) {
    super(message, cause);
  }

}
