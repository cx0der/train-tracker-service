package blog.devrandom;

import blog.devrandom.model.StationBoard;
import blog.devrandom.utils.Utils;
import com.google.gson.Gson;

import java.net.http.HttpClient;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Properties;
import java.util.logging.Logger;

public class RefreshService {
    // property keys
    public static final String API_ENDPOINT = "API_ENDPOINT";
    public static final String API_KEY = "API_KEY";
    public static final String START_STN_CODE = "START_STN_CODE";
    public static final String END_STN_CODE = "END_STN_CODE";
    public static final String FORWARD_START_TIME_KEY = "FORWARD_START_TIME";
    public static final String FORWARD_END_TIME_KEY = "FORWARD_END_TIME";
    public static final String RETURN_START_TIME_KEY = "RETURN_START_TIME";
    public static final String RETURN_END_TIME_KEY = "RETURN_END_TIME";

    // Defaults
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("kk:mm");
    private static final String DEFAULT_FORWARD_START_TIME = "07:30";
    private static final String DEFAULT_FORWARD_END_TIME = "08:30";
    private static final String DEFAULT_RETURN_START_TIME = "17:00";
    private static final String DEFAULT_RETURN_END_TIME = "18:30";

    private static final String CLASS_NAME = RefreshService.class.getName();
    // properties
    private final String apiEndPoint;
    private final String apiKey;
    private final String startStationCode;
    private final String endStationCode;
    private final LocalTime forwardStartTime;
    private final LocalTime forwardEndTime;
    private final LocalTime returnStartTime;
    private final LocalTime returnEndTime;
    private final Gson gson;
    private final Logger logger;

    public RefreshService(Properties runConfig, Gson gson) {
        this(runConfig, gson, Utils.getFineLogger(CLASS_NAME));
    }

    public RefreshService(Properties runConfig, Gson gson, Logger logger) {
        this.gson = gson;
        this.logger = logger;

        if (!runConfig.containsKey(API_ENDPOINT)) {
            RuntimeException ex = new RuntimeException(API_ENDPOINT + " property is missing");
            logger.throwing(CLASS_NAME, "Constructor", ex);
            throw ex;
        }
        String endPoint = runConfig.getProperty(API_ENDPOINT);
        if (!endPoint.startsWith("http")) {
            RuntimeException ex = new RuntimeException("Invalid API endpoint " + endPoint);
            logger.throwing(CLASS_NAME, "Constructor", ex);
            throw ex;
        }
        // If the endpoint had a slash at the end remove it
        apiEndPoint = endPoint.replaceAll("/$", "");

        if (!runConfig.containsKey(API_KEY)) {
            RuntimeException ex = new RuntimeException(API_KEY + " property is missing");
            logger.throwing(CLASS_NAME, "Constructor", ex);
            throw ex;
        }
        apiKey = runConfig.getProperty(API_KEY);

        if (!runConfig.containsKey(START_STN_CODE)) {
            RuntimeException ex = new RuntimeException(START_STN_CODE + " property is missing");
            logger.throwing(CLASS_NAME, "Constructor", ex);
            throw ex;
        }
        startStationCode = runConfig.getProperty(START_STN_CODE);

        if (!runConfig.containsKey(END_STN_CODE)) {
            RuntimeException ex = new RuntimeException(END_STN_CODE + " property is missing");
            logger.throwing(CLASS_NAME, "Constructor", ex);
            throw ex;
        }
        endStationCode = runConfig.getProperty(END_STN_CODE);

        forwardStartTime = getAndParseLocalTime(runConfig, FORWARD_START_TIME_KEY, DEFAULT_FORWARD_START_TIME);
        forwardEndTime = getAndParseLocalTime(runConfig, FORWARD_END_TIME_KEY, DEFAULT_FORWARD_END_TIME);
        returnStartTime = getAndParseLocalTime(runConfig, RETURN_START_TIME_KEY, DEFAULT_RETURN_START_TIME);
        returnEndTime = getAndParseLocalTime(runConfig, RETURN_END_TIME_KEY, DEFAULT_RETURN_END_TIME);
    }

    private LocalTime getAndParseLocalTime(Properties properties, String key, String defaultValue) {
        String value = properties.getProperty(key, defaultValue);
        try {
            return LocalTime.parse(value, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            logger.throwing(CLASS_NAME, "getAndParseLocalTime", e);
            throw new RuntimeException(e);
        }
    }

    public StationBoard refresh(HttpClient httpClient) {
        return null;
    }
}
