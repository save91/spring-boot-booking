package tosi.saverio.booking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tosi.saverio.booking.domain.exception.SlotLengthInvalid;
import tosi.saverio.booking.domain.exception.SlotNotAvailable;
import tosi.saverio.booking.domain.model.Booking;
import tosi.saverio.booking.domain.repository.BookingRepository;
import tosi.saverio.booking.domain.service.BookingCreator;

@RestController
public class BookingController {
    private final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingCreator bookingCreator;


    @RequestMapping(value = "/",method= RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody Booking booking) {
        try {
            bookingCreator.create(booking);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        } catch (SlotNotAvailable | SlotLengthInvalid exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/",method= RequestMethod.GET)
    public Iterable<Booking> findAll() {
        return bookingRepository.findAll();
    }

}
