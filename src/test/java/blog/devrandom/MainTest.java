package blog.devrandom;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    private final PrintStream errorStream = System.err;
    private final PrintStream outStream = System.out;

    private ByteArrayOutputStream errorStreamCaptor;
    private ByteArrayOutputStream outStreamCaptor;

    @BeforeEach
    public void setup() {
        outStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStreamCaptor));

        errorStreamCaptor = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errorStreamCaptor));
    }

    @AfterEach
    public void cleanUp() {
        System.setOut(outStream);
        System.setErr(errorStream);
    }

    @Test
    @DisplayName("Exits with error when No arguments are specified")
    public void noArgs() {
        String[] args = new String[0];
        Main.main(args);
        assertTrue(errorStreamCaptor.toString().contains("Need at most two arguments, either refresh or serve and port"));
    }

    @Test
    @DisplayName("Exits with error when invalid argument is specified")
    public void invalidArgument() {
        String[] args = {"invalid"};
        Main.main(args);
        assertTrue(errorStreamCaptor.toString().contains("Unknown command 'invalid'"));
    }
}
