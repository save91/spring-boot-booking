package tosi.saverio.booking.domain.exception;

public class SlotLengthInvalid extends Exception{
    public SlotLengthInvalid() {
        super("Slot must be length min 1 hour and max 3 hours");
    }
}
