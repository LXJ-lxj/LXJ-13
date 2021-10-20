package org;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Test {
    private static Log DEBUGGER ;
    public static Logger logger=Logger.getLogger(Test.class);
    public static void main(String[] args) {
        //日志级别
        //trace------>debug----->info----->warn----->----error
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");

    }
    public static String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }
}
