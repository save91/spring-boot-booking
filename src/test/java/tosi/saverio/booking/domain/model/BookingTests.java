package tosi.saverio.booking.domain.model;

import static org.junit.Assert.*;
import org.junit.Test;
import tosi.saverio.booking.domain.exception.SlotLengthInvalid;
import tosi.saverio.booking.domain.exception.SlotNotAvailable;
import tosi.saverio.booking.domain.exception.SlotTimeInvalid;

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

    @Test
    public void the_slot_length_should_be_valid() throws SlotLengthInvalid, ParseException {
        Booking booking1 = new Booking();
        booking1.setCourtId(1L);
        booking1.setUserId(1L);
        booking1.setFrom(df.parse("2018-04-03 17:00"));
        booking1.setTo(df.parse("2018-04-03 18:00"));

        Booking booking2 = new Booking();
        booking2.setCourtId(1L);
        booking2.setUserId(1L);
        booking2.setFrom(df.parse("2018-04-03 17:00"));
        booking2.setTo(df.parse("2018-04-03 19:00"));

        Booking booking3 = new Booking();
        booking3.setCourtId(1L);
        booking3.setUserId(1L);
        booking3.setFrom(df.parse("2018-04-03 17:00"));
        booking3.setTo(df.parse("2018-04-03 20:00"));

        assertTrue(booking1.assertSlotLengthIsValid());
        assertTrue(booking2.assertSlotLengthIsValid());
        assertTrue(booking3.assertSlotLengthIsValid());
    }

    @Test(expected = SlotLengthInvalid.class)
    public void the_slot_length_should_be_invalid() throws SlotLengthInvalid, ParseException {
        Booking booking1 = new Booking();
        booking1.setCourtId(1L);
        booking1.setUserId(1L);
        booking1.setFrom(df.parse("2018-04-03 17:00"));
        booking1.setTo(df.parse("2018-04-03 17:59"));

        Booking booking2 = new Booking();
        booking2.setCourtId(1L);
        booking2.setUserId(1L);
        booking2.setFrom(df.parse("2018-04-03 17:00"));
        booking2.setTo(df.parse("2018-04-03 20:01"));

        booking1.assertSlotLengthIsValid();
        booking2.assertSlotLengthIsValid();
    }

    @Test
    public void the_slot_time_should_be_valid() throws ParseException, SlotTimeInvalid {
        Booking booking1 = new Booking();
        booking1.setCourtId(1L);
        booking1.setUserId(1L);
        booking1.setFrom(df.parse("2018-04-03 09:00"));
        booking1.setTo(df.parse("2018-04-03 10:00"));

        Booking booking2 = new Booking();
        booking2.setCourtId(1L);
        booking2.setUserId(1L);
        booking2.setFrom(df.parse("2018-04-03 12:00"));
        booking2.setTo(df.parse("2018-04-03 13:00"));

        Booking booking3 = new Booking();
        booking3.setCourtId(1L);
        booking3.setUserId(1L);
        booking3.setFrom(df.parse("2018-04-03 21:00"));
        booking3.setTo(df.parse("2018-04-03 23:00"));

        assertTrue(booking1.assertTimeIsValid());
        assertTrue(booking2.assertTimeIsValid());
        assertTrue(booking3.assertTimeIsValid());
    }

    @Test(expected = SlotTimeInvalid.class)
    public void the_slot_time_should_be_invalid() throws ParseException, SlotTimeInvalid {
        Booking booking1 = new Booking();
        booking1.setCourtId(1L);
        booking1.setUserId(1L);
        booking1.setFrom(df.parse("2018-04-03 08:59"));
        booking1.setTo(df.parse("2018-04-03 10:00"));

        Booking booking2 = new Booking();
        booking2.setCourtId(1L);
        booking2.setUserId(1L);
        booking2.setFrom(df.parse("2018-04-03 22:00"));
        booking2.setTo(df.parse("2018-04-03 23:01"));

        Booking booking3 = new Booking();
        booking3.setCourtId(1L);
        booking3.setUserId(1L);
        booking3.setFrom(df.parse("2018-04-03 23:00"));
        booking3.setTo(df.parse("2018-04-03 24:00"));

        assertTrue(booking1.assertTimeIsValid());
        assertTrue(booking2.assertTimeIsValid());
        assertTrue(booking3.assertTimeIsValid());
    }

}
