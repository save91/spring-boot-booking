package tosi.saverio.booking.domain.exception;

public class SlotNotAvailable extends Exception{
    public SlotNotAvailable() {
        super("Slot not available");
    }
}
