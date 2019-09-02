package tosi.saverio.booking.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tosi.saverio.booking.domain.exception.ModelNotFound;
import tosi.saverio.booking.domain.exception.SlotLengthInvalid;
import tosi.saverio.booking.domain.exception.SlotNotAvailable;
import tosi.saverio.booking.domain.exception.SlotTimeInvalid;
import tosi.saverio.booking.domain.model.Booking;
import tosi.saverio.booking.domain.model.Court;
import tosi.saverio.booking.domain.model.User;
import tosi.saverio.booking.domain.repository.BookingRepository;
import tosi.saverio.booking.domain.repository.CourtRepository;
import tosi.saverio.booking.domain.repository.UserRepository;
import tosi.saverio.booking.service.Mailer;
import tosi.saverio.booking.service.Sms;

import java.util.List;
import java.util.Optional;

@Service
public class BookingCreator {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CourtRepository courtRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Mailer mailer;

    @Autowired
    private Sms sms;

    public BookingCreator() { }

    public Booking create(Booking booking) throws SlotNotAvailable, SlotLengthInvalid, SlotTimeInvalid, ModelNotFound {
        List<Booking> bookingOfDay = bookingRepository.fetchBookingByCourtAndDay(
            booking.getCourtId(),
            booking.getFrom()
        );

        for (Booking b : bookingOfDay) {
            booking.assertSlotIsAvailable(b);
        }

        booking.assertSlotLengthIsValid();
        booking.assertTimeIsValid();

        Optional<Court> court = courtRepository.findById(booking.getCourtId());
        Optional<User> user = userRepository.findById(booking.getUserId());

        if (!court.isPresent()) {
            throw new ModelNotFound("court");
        }

        if (!user.isPresent()) {
            throw new ModelNotFound("user");
        }

        bookingRepository.save(booking);

        mailer.send(user.get().getEmail(), "Booked");
        sms.send(user.get().getPhone(), "Booked");

        if (bookingRepository.findByUserId(booking.getUserId()).size() == 10) {
            booking.setFree(true);
            bookingRepository.save(booking);
        }

        return booking;
    }
}
