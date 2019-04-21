package org.third.common.http;

/**
 * Represents a media type. Identity service data may be returned in JSON or XML format.
 *
 */
public enum MediaType {
    SAML_ECP(null,null,"ver=\"urn:liberty:paos:2003-08\"; \"urn:oasis:names:tc:SAML:2.0:profiles:SSO:ecp\""),
    APPLICATION_VND_PAOS_XML("application", "vnd.paos+xml"),//
    
    WILDCARD("*", "*"),//
    APPLICATION_XML("application", "xml"),//
    APPLICATION_ATOM_XML("application", "atom+xml"),//
    APPLICATION_XHTML_XML("application", "xhtml+xml"),//
    APPLICATION_SVG_XML("application", "svg+xml"),//
    APPLICATION_JSON("application", "json"),//
    APPLICATION_FORM_URLENCODED("application", "x-www-form-urlencoded"),//
    MULTIPART_FORM_DATA("multipart", "form-data"),///
    APPLICATION_OCTET_STREAM("application", "octet-stream"),//
    TEXT_PLAIN("text", "plain"),//
    TEXT_XML("text", "xml"),//
    TEXT_HTML("text", "html");//

    private String type;
    private String subtype;
    private String name;

    private MediaType(String type, String subtype) {
        this.type = type;
        this.subtype = subtype;
        this.name = type + "/" + subtype;
    } 
    private MediaType(String type, String subtype, String name) {
        this.type = type;
        this.subtype = subtype;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public boolean isWildcardType() {
        return getType().equals("*");
    }

    public String getSubtype() {
        return subtype;
    }

    public boolean isWildcardSubtype() {
        return getSubtype().equals("*");
    }

    public String getName() {
        return name;
    }

    public boolean isCompatible(MediaType other) {
        if (other == null)
            return false;
        if (type.equals("*") || other.type.equals("*"))
            return true;
        if (type.equalsIgnoreCase(other.type) && (subtype.equals("*") || other.subtype.equals("*")))
            return true;
        else
            return type.equalsIgnoreCase(other.type) && subtype.equalsIgnoreCase(other.subtype);
    }

}
