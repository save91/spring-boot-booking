package tosi.saverio.booking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sms {
    private final Logger logger = LoggerFactory.getLogger(Sms.class);

    public void send(String to, String message) {
        logger.debug(to + ": " + message);
    }
}
