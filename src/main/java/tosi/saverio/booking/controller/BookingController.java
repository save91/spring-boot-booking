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
import tosi.saverio.booking.model.entity.Booking;
import tosi.saverio.booking.model.repository.BookingRepository;

import java.util.List;

@RestController
public class BookingController {
    private final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingRepository bookingRepository;
 
    @RequestMapping(value = "/",method= RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody Booking reservation) {
        List<Booking> overlappingBooking = bookingRepository.fetchOverlappingBooking(reservation.getCourtId());

        if (overlappingBooking.size() == 0) {
            bookingRepository.save(reservation);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("The slot was booked", HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/",method= RequestMethod.GET)
    public Iterable<Booking> findAll() {
        return bookingRepository.findAll();
    }

}
