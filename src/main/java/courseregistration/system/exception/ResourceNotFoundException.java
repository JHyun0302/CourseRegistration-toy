package courseregistration.system.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("not found resource");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
