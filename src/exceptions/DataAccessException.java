package exceptions;

public class DataAccessException extends Exception {
    private String message;

    public DataAccessException (String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
