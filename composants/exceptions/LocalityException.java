package composants.exceptions;

public class LocalityException extends Exception{
  private String message;
  public LocalityException(String message){
    this.message = message;
  }
  public String getMessage(){
    return this.message;
  }
}
