package org.third.common.http;

/**
 * Define some constants commonly-used by HTTP request
 *
 */
public enum HttpHeader {
    X_AUTH_TOKEN("X-Auth-Token"),//
    PAOS("PAOS"),
///////////////
    ACCEPT("Accept"),//
    ACCEPT_CHARSET("Accept-Charset"),//
    ACCEPT_ENCODING("Accept-Encoding"),//
    ACCEPT_LANGUAGE("Accept-Language"),//
    AUTHORIZATION("Authorization"),//
    CACHE_CONTROL("Cache-Control"),//
    CONTENT_ENCODING("Content-Encoding"),//
    CONTENT_LANGUAGE("Content-Language"),//
    CONTENT_LENGTH("Content-Length"),//
    CONTENT_LOCATION("Content-Location"),//
    CONTENT_TYPE("Content-Type"),//
    DATE("Date"),//
    ETAG("ETag"),//
    EXPIRES("Expires"),//
    HOST("Host"),//
    IF_MATCH("If-Match"),//
    IF_MODIFIED_SINCE("If-Modified-Since"),//
    IF_NONE_MATCH("If-None-Match"),//
    IF_UNMODIFIED_SINCE("If-Unmodified-Since"),//
    LAST_MODIFIED("Last-Modified"),//
    LOCATION("Location"),//
    USER_AGENT("User-Agent"),//
    VARY("Vary"),//
    WWW_AUTHENTICATE("WWW-Authenticate"),//
    REFERER("referer"),//
    COOKIE("Cookie"),//
    SET_COOKIE("Set-Cookie");
    private String name;

    private HttpHeader(String name_) {
        this.name = name_;
    }

    public String getName() {
        return name;
    }
}