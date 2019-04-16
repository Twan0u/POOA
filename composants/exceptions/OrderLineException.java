package composants.exceptions;

public class OrderLineException extends Exception{
  private String message;
  public OrderLineException(String message){
    this.message = message;
  }
  public String getMessage(){
    return this.message;
  }
}
