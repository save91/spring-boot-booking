package tosi.saverio.booking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mailer {
    private final Logger logger = LoggerFactory.getLogger(Mailer.class);

    public void send(String to, String message) {
        logger.debug(to + ": " + message);
    }
}
