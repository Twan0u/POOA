package exceptions;

public class DataBackupException extends  Exception {
    private String message;

    public DataBackupException(String message) {
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
