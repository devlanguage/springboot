package org.third.common.utils;

/**
 * Created by Huailong Tang(Jason) on 2017/9/19.
 */

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * This is utils handle lambda occurs exception
 */
public class LambdaExceptionUtil {

  @FunctionalInterface
  public interface Function_WithException<T,R,E extends Exception> {
    R apply(T t) throws E;
  }

  @FunctionalInterface
  public interface Consumer_WithException<T,E extends Exception> {
    void apply(T t) throws E;
  }

  public static <T,R,E extends Exception> Function<T,R> rethrowFunction(Function_WithException<T,R,E> function) throws E{
    return t -> {
      try{
        return function.apply(t);
      }catch (Exception exception) {
        throwActualException(exception);
        return null;
      }
    };
  }

  public static <T, E extends Exception> Consumer<T> rethrowConsumer(Consumer_WithException<T,E> consumer) throws E {
    return t -> {
      try {
         consumer.apply(t);
      }catch (Exception ex) {
        throwActualException(ex);
      }
    };
  }

  private static <E extends Exception> void throwActualException(Exception exception) throws E {
    throw (E) exception;
  }

}
