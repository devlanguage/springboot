package org.third.common.exception;

/**
 * Created by qli4 on 2017/9/26.
 */
public class InvalidCertificateException extends RuntimeException{

    public InvalidCertificateException() { super(); }

    public InvalidCertificateException(String msg) { super(msg); }

    public InvalidCertificateException(Throwable cause) { super(cause); }

    public InvalidCertificateException(String msg, Throwable cause) { super(msg, cause); }
}
