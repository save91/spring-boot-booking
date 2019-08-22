package tosi.saverio.booking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tosi.saverio.booking.dto.ReservationDTO;

@RestController
public class BookingController {
    private final Logger logger = LoggerFactory.getLogger(BookingController.class);
 
    @RequestMapping(value = "/",method= RequestMethod.POST)
    public String create(@RequestBody ReservationDTO reservation) {
        logger.info("userId " + reservation.getUserId());
        logger.info("courtId " + reservation.getCourtId());
        logger.info("from " + reservation.getFrom());
        logger.info("to " + reservation.getTo());

        return "Created";
    }

}
