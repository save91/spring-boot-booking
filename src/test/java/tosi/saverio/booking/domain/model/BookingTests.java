package tosi.saverio.booking.domain.model;

import static org.junit.Assert.*;
import org.junit.Test;
import tosi.saverio.booking.domain.exception.SlotNotAvailable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BookingTests {
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Test
    public void the_slot_should_be_available() throws SlotNotAvailable, ParseException {
        Booking booking1 = new Booking();
        booking1.setCourtId(1L);
        booking1.setUserId(1L);
        booking1.setFrom(df.parse("2018-04-03 17:00"));
        booking1.setTo(df.parse("2018-04-03 18:00"));

        Booking booking2 = new Booking();
        booking2.setCourtId(1L);
        booking2.setUserId(1L);
        booking2.setFrom(df.parse("2018-04-03 19:00"));
        booking2.setTo(df.parse("2018-04-03 20:00"));

        Booking booking3 = new Booking();
        booking3.setCourtId(1L);
        booking3.setUserId(1L);
        booking3.setFrom(df.parse("2018-04-03 18:00"));
        booking3.setTo(df.parse("2018-04-03 19:00"));

        assertTrue(booking1.assertSlotIsAvailable(booking3));
        assertTrue(booking2.assertSlotIsAvailable(booking3));
    }

    @Test(expected = SlotNotAvailable.class)
    public void the_slot_should_be_unavailable() throws SlotNotAvailable, ParseException {
        Booking booking = new Booking();
        booking.setCourtId(1L);
        booking.setUserId(1L);
        booking.setFrom(df.parse("2018-04-03 17:00"));
        booking.setTo(df.parse("2018-04-03 18:00"));

        Booking notAvailableBooking = new Booking();
        notAvailableBooking.setCourtId(1L);
        notAvailableBooking.setUserId(1L);
        notAvailableBooking.setFrom(df.parse("2018-04-03 17:30"));
        notAvailableBooking.setTo(df.parse("2018-04-03 18:30"));

        notAvailableBooking.assertSlotIsAvailable(booking);
    }
}
