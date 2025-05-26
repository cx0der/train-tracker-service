package blog.devrandom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static org.junit.jupiter.api.Assertions.*;

class StdConsoleHandlerTest {
    private final ByteArrayOutputStream errorStreamCaptor = new ByteArrayOutputStream();
    private final ByteArrayOutputStream outStreamCaptor = new ByteArrayOutputStream();

    private Logger logger;

    @BeforeEach
    public void setup() {
        logger = Logger.getLogger(StdConsoleHandlerTest.class.getName());
        logger.setUseParentHandlers(false);
        logger.addHandler(new StdConsoleHandler(new SimpleFormatter(), outStreamCaptor, errorStreamCaptor));
    }

    @Test
    @DisplayName("Writes info logs to out stream")
    public void writesToOutStream() {
        logger.info("info log message");

        assertTrue(outStreamCaptor.toString().contains("info log message"));
        assertTrue(errorStreamCaptor.toString().isEmpty());
    }

    @Test
    @DisplayName("Writes warnings to error stream")
    public void writesToErrorStream() {
        logger.warning("This is a warning message");

        assertTrue(errorStreamCaptor.toString().contains("This is a warning message"));
        assertTrue(outStreamCaptor.toString().isEmpty());
    }
}
