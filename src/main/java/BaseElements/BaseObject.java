package BaseElements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseObject {
    protected final int DEFAULT_TIMEOUT = 30;
    protected final int DEFAULT_SLEEP = 500;

    private static Logger LOGGER = LoggerFactory.getLogger("TAE_Logger");

    public BaseObject() {
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
