package org.third.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sl4jLoggerTest {
    public static final Logger logger =LoggerFactory.getLogger(Sl4jLoggerTest.class);
    public static void main(String[] args) {
        String str1=null;
        logger.info("str1=" + str1);
    }

}
