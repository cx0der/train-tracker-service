package blog.devrandom;

import blog.devrandom.utils.Utils;

import java.util.logging.Logger;

public class Main {
    public static final String REFRESH_COMMAND = "refresh";
    public static final String SERVE_COMMAND = "serve";

    public static void main(String[] args) {
        final Logger logger = Utils.getFineLogger(Main.class.getName());
        logger.info("Train Tracker version: 1.0.0");

        if (args.length > 2 || args.length < 1) {
            // We want at least 1 and at most 2 arguments
            logger.severe("Need at most two arguments, either refresh or serve and port");
            return;
        }

        switch (args[0]) {
            case REFRESH_COMMAND, SERVE_COMMAND -> {
            }
            default -> logger.severe("Unknown command '%s'".formatted(args[0]));
        }
    }
}
