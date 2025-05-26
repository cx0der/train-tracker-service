package blog.devrandom;

import java.io.OutputStream;
import java.util.logging.ErrorManager;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class StdConsoleHandler extends Handler {

    private final OutputStream out;
    private final OutputStream err;

    public StdConsoleHandler() {
        this(new SimpleFormatter(), System.out, System.err);
    }

    public StdConsoleHandler(Formatter formatter, OutputStream out, OutputStream err) {
        super();
        setFormatter(formatter);
        this.out = out;
        this.err = err;
    }

    @Override
    public void publish(LogRecord record) {
        try {
            String message = getFormatter().format(record);
            if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
                err.write(message.getBytes());
            } else {
                out.write(message.getBytes());
            }
        } catch (Exception e) {
            reportError(e.getMessage(), e, ErrorManager.FORMAT_FAILURE);
        }
    }

    @Override
    public void flush() {
    }

    @Override
    public void close() throws SecurityException {
    }
}
