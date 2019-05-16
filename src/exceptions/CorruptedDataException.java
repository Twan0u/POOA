package exceptions;

public class CorruptedDataException extends Exception{
    private String message;

    public CorruptedDataException(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}