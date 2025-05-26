package blog.devrandom.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.Optional;
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

    public static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Optional.class, new OptionalTypeAdapter<>())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
    }
}
