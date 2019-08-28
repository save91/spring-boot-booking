package tosi.saverio.booking.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tosi.saverio.booking.domain.exception.SlotNotAvailable;
import tosi.saverio.booking.domain.model.Booking;
import tosi.saverio.booking.domain.repository.BookingRepository;

import java.util.List;

@Service
public class BookingCreator {

    @Autowired
    private BookingRepository bookingRepository;

    public BookingCreator() { }

    public Booking create(Booking booking) throws SlotNotAvailable {
        List<Booking> bookingOfDay = bookingRepository.fetchBookingByCourtAndDay(
            booking.getCourtId(),
            booking.getFrom()
        );

        for (Booking b : bookingOfDay) {
            booking.assertSlotIsAvailable(b);
        }

        bookingRepository.save(booking);

        return booking;
    }
}
