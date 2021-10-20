package proxy4.log;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class TestLog4j {

    private static Logger logger = Logger.getLogger(TestLog4j.class);

    public static void main(String[] args) {
        if (logger.isInfoEnabled()) {
            // 如果日志内容比较复杂的话，可以通过这种判断，提升效率
            logger.info("this is a info msg");
            System.out.print("info...");
        }
        if (logger.isDebugEnabled()) {
            logger.debug("this is a debug msg");
            System.out.print("debug...");
        }
        if (logger.isEnabledFor(Level.ERROR)) {
            logger.error("this is a error msg");
            System.out.print("error...");
        }
        if (logger.isEnabledFor(Level.WARN)) {
            logger.warn("this is a warn msg");
            System.out.print("warn...");
        }
    }
}
