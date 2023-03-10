package courseregistration.system.service.exception;

public class LoginIdException extends RuntimeException {
    public LoginIdException() {
        super("Failed: Login!");
    }

    public LoginIdException(String message) {
        super(message);
    }
}
