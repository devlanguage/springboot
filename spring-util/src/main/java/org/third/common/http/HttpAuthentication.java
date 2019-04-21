package org.third.common.http;

/***
 * Constants used to define the http authentication type
 *
 */
public enum HttpAuthentication {
    BEARER("Bearer"),    //
    BASIC("Basic"),     //
    DIGEST("Digest"),     //
    NTLM("ntlm"),     //
    NEGOTIATE("negotiate");//
    private String name;

    private HttpAuthentication(String name_) {
        this.name = name_;
    }

    public String getName() {
        return name;
    }
}