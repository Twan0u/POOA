package exceptions;

public class DataModificationException extends Exception {
    private String message;

    public DataModificationException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
