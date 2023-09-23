package AutoFramework.Utilities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log {
    static Logger mainLogger = LogManager.getLogger(Log.class.getName());

    public static void startTestDetails(String testName){
        mainLogger.info("------------------------------");
        mainLogger.info("--------- "+ testName +" ---------");
        mainLogger.info("------------------------------");
    }

    public  static void endTestDetails(String testName){
        mainLogger.info("------------------------------");
        mainLogger.info("---------    END   -----------");
        mainLogger.info("------------------------------");
    }

    public static void debug(String message){
        mainLogger.debug(message);
    }

    public static void info(String message){
        mainLogger.info(message);
    }

    public static void error(String message){
        mainLogger.error(message);
    }

    public static void warn(String message){
        mainLogger.warn(message);
    }
}
