package tosi.saverio.booking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tosi.saverio.booking.model.entity.Booking;
import tosi.saverio.booking.model.repository.BookingRepository;

@RestController
public class BookingController {
    private final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingRepository bookingRepository;
 
    @RequestMapping(value = "/",method= RequestMethod.POST)
    public String create(@RequestBody Booking reservation) {
        bookingRepository.save(reservation);

        return "Created";
    }

    @RequestMapping(value = "/",method= RequestMethod.GET)
    public Iterable<Booking> findAll() {
        return bookingRepository.findAll();
    }

}
