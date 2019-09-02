package tosi.saverio.booking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Mailer {
    private final Logger logger = LoggerFactory.getLogger(Mailer.class);

    public void send(String to, String message) {
        logger.info(to + ": " + message);
    }
}
