package org.third.common.http;

/**
 * 
 * Represent one http request opeartion to be sent
 */
public enum HttpMethod {
    POST(true),      //
    PUT(true),      //
    PATCH(true),    //
    DELETE(true),  //
    GET(false),     //
    HEAD(false),    //
    OPTIONS(false), //
    TRACE(false);//
    private boolean supported;
    private boolean writable;

    private HttpMethod() {
        this(true);
    }

    private HttpMethod(boolean writable) {
        this.supported = true;
        this.writable = writable;
    }

    /**
     * Indicate if current http operation have been supported by at least one interface
     * 
     * @return
     */
    public boolean isSupported() {
        return supported;
    }

    /**
     * Indicate if HTTP stream can be written
     * 
     * @return the writable of HttpMethod
     */
    public boolean isWritable() {
        return writable;
    }

    public String getName() {
        return name();
    }
}
