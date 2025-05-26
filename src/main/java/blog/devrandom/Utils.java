package blog.devrandom;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

    public static Logger getFineLogger(String className) {
        Logger logger = Logger.getLogger(className);
        logger.setLevel(Level.FINE);
        logger.setUseParentHandlers(false);
        logger.addHandler(new StdConsoleHandler());
        return logger;
    }
}
