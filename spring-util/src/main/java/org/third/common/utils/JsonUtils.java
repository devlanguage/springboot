package org.third.common.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

class HibernateAwareObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = 151217594276335691L;

    public HibernateAwareObjectMapper() {
        this.registerModule(new JaxbAnnotationModule());
    }
}

/**
 * 
 * Provides one utilities to convert between java object and json data.
 *
 */
public class JsonUtils {
    /**
     * 
     * @param clazz
     * @return
     * @throws JsonProcessingException
     */
    public static final String marshall(Object clazz) throws JsonProcessingException {
        HibernateAwareObjectMapper mapper = new HibernateAwareObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        return mapper.writeValueAsString(clazz);
    }

    /**
     * Converts one json formatted string to java object specified by {@code clazz}
     * 
     * @param jsonString original json string
     * @param clazz which java class used to constrcut the java object
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static final <T> T unmarshall(String jsonString, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        HibernateAwareObjectMapper mapper = new HibernateAwareObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);
        return mapper.readValue(jsonString, clazz);
    }
    
    public static void testJackson(String[] args) throws IOException {
        RequestToken rt = new RequestToken();
        Set<String> tenants = new HashSet<String>();
        tenants.addAll(Arrays.asList(new String[] { "test1", "test2" }));
        rt.setSecondaryTenants(tenants);
        System.out.println(marshall(rt));
        
        String rtString=
                "{ \r\n" + 
                "  \"return_uri\":\"http://localhost:8080/app1/ssoLogout?backAppOnLogout=true\",\r\n" + 
                "  \"support_error_callback\":false,\r\n" + 
                "  \"tenant\":\"provider\",\r\n" + 
                "  \"clientType\":\"mobile\",\r\n" + 
                "  \"secondaryTenants\":[\"tenant1\", \"tenant2\"]\r\n" + 
                "}";
        System.out.println(unmarshall(rtString, RequestToken.class));
    }

    static class RequestToken {
        private Set<String> secondaryTenants;

        /**
         * Get the secondaryTenants value.
         *
         * @return the value of secondaryTenants
         */
        public Set<String> getSecondaryTenants() {
            return this.secondaryTenants;
        }

        /**
         * Set the secondaryTenants value.
         *
         * @param secondaryTenants the secondaryTenants to set
         */
        public void setSecondaryTenants(Set<String> secondaryTenants) {
            this.secondaryTenants = secondaryTenants;
        }
        @Override
        public String toString() {
            return secondaryTenants.toString();
        }

    }

}