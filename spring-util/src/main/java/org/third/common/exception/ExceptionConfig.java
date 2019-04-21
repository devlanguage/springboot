package org.third.common.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huailong Tang(Jason) on 2017/6/26.
 */
public class ExceptionConfig {
    public static final List<String> URL_WILL_RETRY_LIST = new ArrayList<String>(){{
        add("/urest/v1.1/deployment/.+/installation");
    }};

}
