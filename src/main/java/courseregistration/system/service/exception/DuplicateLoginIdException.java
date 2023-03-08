package courseregistration.system.service.exception;

public class DuplicateLoginIdException extends RuntimeException {
    public DuplicateLoginIdException() {
        super("Failed: Already Exist ID!");
    }

    public DuplicateLoginIdException(String message) {
        super(message);
    }
}
