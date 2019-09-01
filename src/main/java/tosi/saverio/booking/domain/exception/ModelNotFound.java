package tosi.saverio.booking.domain.exception;

public class ModelNotFound extends Exception{
    public ModelNotFound(String model) {
        super(model + " not found");
    }
}
