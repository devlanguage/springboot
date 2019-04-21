package org.third.common.exception;

/**
 * Created by liguoqi on 2018/4/2.
 */
public class ImagePullBackOffException extends RuntimeException {

  public ImagePullBackOffException() {
  }

  public ImagePullBackOffException(String message) {
    super(message);
  }

  public ImagePullBackOffException(Throwable cause) {
    super(cause);
  }

  public ImagePullBackOffException(String message, Throwable cause) {
    super(message, cause);
  }

}
