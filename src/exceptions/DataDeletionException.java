package exceptions;

public class DataDeletionException extends Exception{
    private String message;

    public DataDeletionException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
