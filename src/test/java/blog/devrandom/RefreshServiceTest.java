package blog.devrandom;

import blog.devrandom.utils.Utils;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Properties;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class RefreshServiceTest {

    private final Gson gson = new Gson();
    private Logger logger;

    @BeforeEach
    public void setUp() {
        logger = Utils.getFineLogger(RefreshService.class.getName());
    }


    @Test
    @DisplayName("empty properties throws Exception")
    public void emptyProperties() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> new RefreshService(new Properties(), gson, logger));
        assertEquals("API_ENDPOINT property is missing", exception.getMessage());
    }

    @Test
    @DisplayName("Missing properties throws Exception")
    public void missingProperties() {
        Properties properties = new Properties();
        properties.put(RefreshService.API_ENDPOINT, "https://example.com");
        properties.put(RefreshService.API_KEY, "key");
        properties.put(RefreshService.END_STN_CODE, "MAN");
        RuntimeException exception = assertThrows(RuntimeException.class, () -> new RefreshService(properties, gson, logger));
        assertEquals("START_STN_CODE property is missing", exception.getMessage());
    }

    @Test
    @DisplayName("Throws exception for invalid API endpoint")
    public void invalidApiUrl() {
        Properties properties = new Properties();
        properties.put(RefreshService.API_ENDPOINT, "e");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> new RefreshService(properties, gson, logger));
        assertEquals("Invalid API endpoint e", exception.getMessage());
    }
}