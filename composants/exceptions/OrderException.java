package composants.exceptions;

public class OrderException extends Exception{
  private String message;
  public OrderException(String message){
    this.message = message;
  }
  public String getMessage(){
    return this.message;
  }
}
