package tosi.saverio.booking.domain.exception;

public class SlotTimeInvalid extends Exception{
    public SlotTimeInvalid() {
        super("The camp can be booked from 9 to 23");
    }
}
